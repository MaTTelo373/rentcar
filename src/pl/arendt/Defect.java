package pl.arendt;

public enum Defect {
    NONE(0d),
    BREAKS(10d),
    SUSPENSION(20d),
    ENGINE(100d),
    BODY(50d),
    TRANSMISION(50d),
    TO_CLEAN(2d);


    Double valueToAdd;


    Defect(Double valueToAdd) {
        this.valueToAdd = valueToAdd;
    }

    public double valueToAdd() {
        return valueToAdd;
    }
}
