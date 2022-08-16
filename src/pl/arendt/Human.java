package pl.arendt;

import pl.arendt.devices.Device;
import pl.arendt.devices.Car;

public class Human implements Owner {

    public enum Sex {
        MAN,
        FEMALE,
        UNKNOWN;
    }

    private String firstName;

    private String lastName;

    private Double cash;

    private Sex sex;

    public Human(Sex sex, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.cash = 0d;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cash=" + cash +
                ", sex=" + sex +
                '}';
    }

    @Override
    public String getDescr() {
        return toString();
    }

}
