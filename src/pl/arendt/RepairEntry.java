package pl.arendt;

public class RepairEntry {

    private Defect defect;

    private Mechanic mechanic;

    private Double price;

    private Boolean repaired;

    private Boolean damaged;

    public RepairEntry(Defect defect, Mechanic mechanic, Double price, Boolean repaired, Boolean damaged) {
        this.defect = defect;
        this.mechanic = mechanic;
        this.price = price;
        this.repaired = repaired;
        this.damaged = damaged;
    }


    public Defect getDefect() {
        return defect;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public Double getPrice() {
        return price;
    }


    public Boolean getRepaired() {
        return repaired;
    }

    public Boolean getDamaged() {
        return damaged;
    }

    @Override
    public String toString() {
        return "RepairEntry{" +
                "defect=" + defect +
                ", mechanic=" + (mechanic != null ? (mechanic.getFirstName() + " " + mechanic.getLastName()) : "SELLER") +
                ", price=" + price +
                ", repaired=" + repaired +
                ", damaged=" + damaged +
                '}';
    }
}
