package pl.arendt;

import java.util.Map;
import java.util.Random;


public class Mechanic extends Human {

    private int probabilityOfFail;

    private int probabilityOfDemage;

    private Map<Defect, Double> prices;

    public Mechanic(Sex sex, String firstName, String lastName,
                    int probabilityOfFail, int probabilityOfDemage,
                    Map<Defect, Double> prices) {
        super(sex, firstName, lastName);
        this.probabilityOfFail = probabilityOfFail;
        this.probabilityOfDemage = probabilityOfDemage;
        this.prices = prices;
    }


    public int getProbabilityOfFail() {
        return probabilityOfFail;
    }

    public int getProbabilityOfDemage() {
        return probabilityOfDemage;
    }

    public Map<Defect, Double> getPrices() {
        return prices;
    }


    public RepairEntry fixCar(StoreCar car, Defect defect) {
        Random rand = new Random();
        if (rand.nextInt(100)  <= this.probabilityOfDemage) {
            return new RepairEntry(defect, this, 0d, false, true);
        }
        if (rand.nextInt(100)  <= this.probabilityOfFail) {
            return new RepairEntry(defect, this, 0d, false, false);
        }
        return new RepairEntry(defect, this, prices.get(defect)*car.getSegment().getFixRate(), true, false);
    }

    @Override
    public String toString() {
        return "Mechanic{" + super.toString() +
                "probabilityOfFail=" + probabilityOfFail +
                ", probabilityOfDemage=" + probabilityOfDemage +
                ", prices=" + prices +
                "}";
    }
}
