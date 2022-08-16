package pl.arendt.devices;

import pl.arendt.Owner;

public class Car extends Device {


    private Double engineSize;
    private String fuelType;

    private Double value;

    public Car(String model, String producer, Integer yearOfProduction, Double value) {
        super(producer, model, yearOfProduction);

        this.engineSize = 1.0d;
        this.fuelType = "gas";
        this.value = value;
    }

    public void turnOn() {
        System.out.println("Engine is on, you will need tank me soon!");
    }


    public Double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Double engineSize) {
        this.engineSize = engineSize;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        if (value < 0) {
            throw new IllegalStateException("Value cannot be less than 0!");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return "Car{" + super.toString() +
                "engineSize=" + engineSize +
                ", fuelType='" + fuelType + '\'' +
                ", value=" + value +
                "} ";
    }

    public String getShortDescr() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (engineSize != null ? !engineSize.equals(car.engineSize) : car.engineSize != null) return false;
        if (fuelType != null ? !fuelType.equals(car.fuelType) : car.fuelType != null) return false;
        return value != null ? value.equals(car.value) : car.value == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (engineSize != null ? engineSize.hashCode() : 0);
        result = 31 * result + (fuelType != null ? fuelType.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
