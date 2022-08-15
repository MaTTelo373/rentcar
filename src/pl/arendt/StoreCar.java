package pl.arendt;

import java.util.ArrayList;
import java.util.List;

import pl.arendt.devices.Car;


public class StoreCar extends Car {


    public enum Segment {
        BUDGET(0.7d),
        STANDARD(1d),
        PREMIUM(1.5d);

        private Double fixRate; // o ile trzeba powiekszyc/zmniejszyc koszt naprawy

        Segment(Double fixRate) {
            this.fixRate = fixRate;
        }

        public Double getFixRate() {
            return fixRate;
        }
    }


    private Owner owner;

    private String color;

    private Double mileage;

    private Segment segment;

    private List<RepairEntry> repairEntries;

    public StoreCar(Owner owner, String model, String producer, String color, Segment segment, Integer yearOfProduction, Double value, Double millage) {
        super(model, producer, yearOfProduction, value);
        this.owner = owner;
        this.color = color;
        this.segment = segment;
        this.mileage = millage;
        this.repairEntries = new ArrayList<RepairEntry>();
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getColor() {
        return color;
    }

    public Double getMileage() {
        return mileage;
    }

    public Segment getSegment() {
        return segment;
    }

    public void addRepairEntry(RepairEntry repairEntry) {
        repairEntries.add(repairEntry);
    }

    @Override
    public String toString() {
        return "StoreCar{" + super.toString() +
                " owner=" + owner.getDescr() +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                ", segment=" + segment +
                ", repairEntries=" + repairEntries +
                "} ";
    }

    public String getShortDescr() {
        return "StoreCar{" + super.getShortDescr() +
                " color='" + color + '\'' +
                ", mileage=" + mileage +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StoreCar storeCar = (StoreCar) o;

        if (color != null ? !color.equals(storeCar.color) : storeCar.color != null) return false;
        if (mileage != null ? !mileage.equals(storeCar.mileage) : storeCar.mileage != null) return false;
        return segment == storeCar.segment;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (mileage != null ? mileage.hashCode() : 0);
        result = 31 * result + (segment != null ? segment.hashCode() : 0);
        return result;
    }

}
