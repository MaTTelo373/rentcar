package pl.arendt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CarsStore implements Owner {

    private String name;

    private Double accountValue;


    private Double tradeMargin;

    private List<StoreCar> stock;

    private List<StoreCar> sold;

    private List<Mechanic> knownMechanics;

    private List<StoreTransaction> transactions;

    private List<Human> potentialClients;

    public CarsStore(String name, Double accountValue, Double tradeMargin) {
        this.name = name;
        this.accountValue = accountValue;
        this.tradeMargin = tradeMargin;
        this.transactions = new ArrayList<StoreTransaction>();
        this.stock = new ArrayList<StoreCar>();
        this.sold = new ArrayList<StoreCar>();
        this.knownMechanics = new ArrayList<Mechanic>();
        this.potentialClients = new ArrayList<Human>();
    }

    @Override
    public String getDescr() {
        return "Car store: " + this.name;
    }

    public void printStock() {
        System.out.println("Stock:");
        int i = 1;
        for (StoreCar car : stock) {
            System.out.println(i + ": " + car.toString());
            i++;
        };
    }

    public void printSold() {
        System.out.println("Sold:");
        stock.stream().forEach(e->System.out.println(e.toString()));
    }

    public void addPotentialClient(Human client) {
        this.potentialClients.add(client);
    }

    public Human getPotentialClient(int clientNum) {
        clientNum--; // od zera
        if (clientNum >= 0 && clientNum < potentialClients.size()) {
            return this.potentialClients.get(clientNum);
        }
        return null;
    }

    public void printPotentialClients() {
        System.out.println("Potential clients:");
        int i = 1;
        for (Human man : this.potentialClients) {
            System.out.println(i + ": " + man.toString());
            i++;
        };
    }

    public void addKnownMechanic(Mechanic mechanic) {
        this.knownMechanics.add(mechanic);
    }

    public List<Mechanic> getKnownMechanics() {
        return knownMechanics;
    }

    private void registerTransaction(StoreTransaction storeTransaction) {
        this.transactions.add(storeTransaction);
        this.accountValue += storeTransaction.getAmount();
    }

    public void fixCar(int carNum, Defect defect) {
        carNum--; // od zera
        if (defect != null && defect != Defect.NONE && carNum >= 0 && carNum < stock.size()) {
            StoreCar car = this.stock.get(carNum);
            for(Mechanic mechanic : knownMechanics) { // od najtanszego
                RepairEntry rEntry = mechanic.fixCar(car, defect);
                if (rEntry.getRepaired()) {
                    registerTransaction(new StoreTransaction(new Date(),
                                              StoreTransaction.TransactionType.REPAIR,
                                              -rEntry.getPrice(), car, mechanic));
                    car.setValue((100 + rEntry.getDefect().valueToAdd())*car.getValue()/100);
                    car.addRepairEntry(rEntry);
                    break;
                }
            }
        }
    }

    public Boolean buyCar(StoreCar car) {
        if (this.accountValue >= car.getValue()) {
            this.stock.add(car);
            registerTransaction(new StoreTransaction(
                    new Date(), StoreTransaction.TransactionType.BUY, -car.getValue(), car, (Human)car.getOwner()));
            car.setOwner(this);

            // wymyj
            RepairEntry rEntry = new RepairEntry(Defect.TO_CLEAN, null, 0d, true, false);
            car.addRepairEntry(rEntry);
            registerTransaction(new StoreTransaction(
                    new Date(), StoreTransaction.TransactionType.CLEAN,-0.02d * car.getValue(), car, null));

            return true;
        }
        return false;
    }

    public StoreCar sellCar(int carNum, Human buyer) {
        carNum--; // od zera
        if (carNum >= 0 && carNum < stock.size()) {
            StoreCar car = this.stock.get(carNum);
            final Double finalPrice = car.getValue() * (1d + this.tradeMargin);// marza
            if (finalPrice > buyer.getCash()) {

                RepairEntry rEntry = new RepairEntry(Defect.TO_CLEAN, null, 0d, true, false);
                car.addRepairEntry(rEntry);
                registerTransaction(new StoreTransaction(
                        new Date(), StoreTransaction.TransactionType.CLEAN,0.02d * car.getValue(), car, null));

                stock.remove(car);
                car.setOwner(buyer);
                registerTransaction(new StoreTransaction(
                        new Date(), StoreTransaction.TransactionType.SELL, finalPrice, car, buyer));
                buyer.setCash(buyer.getCash() - finalPrice);

                return car;
            }
        }
        return null;
    }

    public Double getPrice(StoreCar car) {
        Double finalPrice = car.getValue() * (1d + this.tradeMargin);// marza
        return finalPrice;
    }


    public void printTransactions() {
        System.out.println("Transactions:");
        int i = 1;
        for (StoreTransaction transaction : this.transactions) {
            System.out.println(i + ": " + transaction.toString());
            i++;
        };
    }

    public void printCarFixes(int carNum) {
        carNum--; // od zera
        if (carNum >= 0 && carNum < stock.size()) {
            StoreCar car = this.stock.get(carNum);
            System.out.println("Car repairs of:" + car.getShortDescr());
            int i = 1;
            for (StoreTransaction transaction : this.transactions) {
                if (transaction.getType() == StoreTransaction.TransactionType.REPAIR &&
                   car.equals(transaction.getCar())) {
                    System.out.println(i + ": " + transaction.toString());
                    i++;
                }
            };
        }
    }

    public void printCarCost(int carNum) {
        carNum--; // od zera
        if (carNum >= 0 && carNum < stock.size()) {
            StoreCar car = this.stock.get(carNum);
            System.out.println("Car cost of:" + car.getShortDescr());
            Double costs = 0d;
            for (StoreTransaction transaction : this.transactions) {
                if ((transaction.getType() == StoreTransaction.TransactionType.REPAIR ||
                     transaction.getType() == StoreTransaction.TransactionType.CLEAN)  &&
                        car.equals(transaction.getCar())) {
                    costs += transaction.getAmount();
                }
            };
            System.out.println("Value:" + costs);
        }
    }

    public void buyAdvertisement(double amount) {
        registerTransaction(new StoreTransaction(
                new Date(), StoreTransaction.TransactionType.ADVERTISEMENT, -amount, null, null));
    }



    @Override
    public String toString() {
        return "CarsStore{" +
                "name='" + name + '\'' +
                ", accountValue=" + accountValue +
                ", tradeMargin=" + tradeMargin +
                '}';
    }
}
