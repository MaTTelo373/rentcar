package pl.arendt;

import java.util.Date;

public class StoreTransaction {


    public enum TransactionType {
        CLEAN,
        REPAIR,
        BUY,
        SELL,
        ADVERTISEMENT,
    }


    private Date date;

    private TransactionType type;

    private Double amount;

    private StoreCar car;

    private Human person;


    public StoreTransaction(Date date, TransactionType type, Double amount, StoreCar car, Human person) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.car = car;
        this.person = person;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public StoreCar getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "StoreTransaction{" +
                "date=" + date +
                ", type=" + type +
                ", amount=" + amount +
                ", car=" + (car != null ? car.getShortDescr() : "-") +
                ", person=" + person +
                '}';
    }

}
