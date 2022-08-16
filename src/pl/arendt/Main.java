package pl.arendt;

import java.util.*;

import static pl.arendt.Human.Sex.*;
import static pl.arendt.Main.generateClients;
import static pl.arendt.StoreCar.Segment.*;

public class Main {


    public static int readInt(Scanner keyboard) {
        String input = keyboard.nextLine();
        if(input != null) {
            return Integer.parseInt(input);
        }
        return -1;
    }


    public static void generateClients(CarsStore store) {
        Random random = new Random();

        Human h = new Human(FEMALE,
                List.of("Ewa", "Anna", "Karolina").get(random.nextInt(3)),
                List.of("Mech", "Kowalska", "Nowak").get(random.nextInt(3)));
        h.setCash(11000d* (3 + random.nextInt(5)));
        store.addPotentialClient(h);

        h = new Human(MAN,
                List.of("Tomek", "Jacek", "Jarek").get(random.nextInt(3)),
                List.of("Pipkowski", "Zdrajca", "Wszechmocny").get(random.nextInt(3)));
        h.setCash(13000d* (2 + random.nextInt(5)));
        store.addPotentialClient(h);
    }


    public static void main(String[] args){

        Human me = new Human(MAN, "Mateusz", "Arendt");
        me.setCash(36000d);
        Human boss = new Human(MAN, "Mikolaj", "Karolinski");
        boss.setCash(40000d);

        CarsStore store = new CarsStore("Pruszcz Gd. Autohandel", 1000000d, 10d);

        store.addPotentialClient(me);
        store.addPotentialClient(boss);

        store.addKnownMechanic(new Mechanic(MAN, "Adrian", "Garbaty", 20, 2,
                                 new HashMap<Defect, Double>() {{
                                    put(Defect.BREAKS, 1000d);
                                    put(Defect.SUSPENSION, 2000d);
                                    put(Defect.ENGINE, 3000d);
                                    put(Defect.BODY, 5000d);
                                    put(Defect.TRANSMISION, 2000d);
                                 }}
                               ));

        store.addKnownMechanic(new Mechanic(MAN, "Marian", "Grzelak", 10, 0,
                new HashMap<Defect, Double>() {{
                    put(Defect.BREAKS, 1500d);
                    put(Defect.SUSPENSION, 2500d);
                    put(Defect.ENGINE, 3500d);
                    put(Defect.BODY, 5500d);
                    put(Defect.TRANSMISION, 2500d);
                }}
        ));

        store.addKnownMechanic(new Mechanic(MAN, "Janusz", "Jawor", 0, 0,
                new HashMap<Defect, Double>() {{
                    put(Defect.BREAKS, 2000d);
                    put(Defect.SUSPENSION, 3000d);
                    put(Defect.ENGINE, 4000d);
                    put(Defect.BODY, 6000d);
                    put(Defect.TRANSMISION, 3000d);
                }}
        ));

        List<StoreCar> toBuy = new ArrayList<StoreCar>() {{
                add(new StoreCar(me,"Ceed", "Kia", "white", STANDARD, 2013, 25000d, 120000d));
                add(new StoreCar(me, "Panda", "Fiat", "red", BUDGET, 2017, 22000d, 60000d));
                add(new StoreCar(boss,"Tiguan", "VW", "silver", PREMIUM, 2016, 72000d, 75000d));
                add(new StoreCar(boss,"X3", "BMW", "silver", PREMIUM, 2015, 94000d, 77000d));
                add(new StoreCar(me,"Yaris", "Toyota", "black", BUDGET, 2008, 14000d, 97000d));
        }};



        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n\nWelcome at " + store.toString());
            System.out.println("\nEnter command (X to exit):");
            System.out.println("1 - print cars to buy");
            System.out.println("2 - print stocked cars");
            System.out.println("3 - print transactions");
            System.out.println("4 - print car's repairs");
            System.out.println("5 - print car's cost");
            System.out.println("6 - print potential clients");
            System.out.println("7 - buy a car");
            System.out.println("8 - repair a car");
            System.out.println("9 - sell a car");
            System.out.println("10 - buy advertisement");
            System.out.println("99 - exit");

               int action = readInt(keyboard);
               System.out.println("Your input is : " + action);
               switch(action) {
                   case 99:
                       System.out.println("Exit program");
                       exit = true;
                       break;
                   case 1:
                       int i = 1;
                       for (StoreCar car : toBuy) {
                           System.out.println(i + ": " + car.toString());
                           i++;
                       };
                       break;
                   case 2:
                       store.printStock();
                       break;
                   case 3:
                       store.printTransactions();
                       break;
                   case 4:
                       System.out.println("enter number of car");
                       store.printStock();
                       store.printCarFixes(readInt(keyboard));
                       break;
                   case 5:
                       System.out.println("enter number of car");
                       store.printStock();
                       store.printCarCost(readInt(keyboard));
                       break;
                   case 6:
                       store.printPotentialClients();
                       break;
                   case 7:
                       System.out.println("enter number of car to buy");
                       i = 1;
                       for (StoreCar car : toBuy) {
                           System.out.println(i + ": " + car.toString());
                           i++;
                       };
                       int carNum = readInt(keyboard) - 1; // od zera
                       if (carNum >= 0 && carNum < toBuy.size()) {
                           StoreCar car = toBuy.get(carNum);
                           store.buyCar(car);
                           toBuy.remove(car);
                       }
                       break;
                   case 8:
                       System.out.println("enter number of defect");
                       for (Defect d : Defect.values()) {
                           System.out.println(d.ordinal() + ": " + d);
                       }
                       Defect f = Defect.values()[readInt(keyboard)];
                       System.out.println("enter number of car");
                       store.printStock();
                       store.fixCar(readInt(keyboard), f);
                       break;
                   case 9:
                       System.out.println("enter number of client");
                       store.printPotentialClients();
                       Human client = store.getPotentialClient(readInt(keyboard));
                       System.out.println("enter number of car");
                       store.printStock();
                       toBuy.add(store.sellCar(readInt(keyboard), client));
                       generateClients(store);
                       break;
                   case 10:
                       Random random = new Random();
                       Double sum = 1000d*(2 + random.nextInt(5));
                       System.out.println("buying advertisement for " + sum);
                       store.buyAdvertisement(sum);
                       generateClients(store);
                       generateClients(store);
                       break;
               }

        }
        keyboard.close();

    }
}
