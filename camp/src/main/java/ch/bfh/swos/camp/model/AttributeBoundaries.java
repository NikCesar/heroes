package ch.bfh.swos.camp.model;

public class AttributeBoundaries {

    private int atkMin;
    private int atkMax;

    private int defMin;
    private int defMax;

    private double hpMin;
    private double hpMax;

    private int initiativeMin;
    private int initiativeMax;

    private double critChanceMin;
    private double critChanceMax;

    private double dodgeChanceMin;
    private double dodgeChanceMax;



    public AttributeBoundaries(int atkMin, int atkMax, int defMin, int defMax, double hpMin, double hpMax,
                               int initiativeMin, int initiativeMax, double critChanceMin, double critChanceMax,
                               double dodgeChanceMin, double dodgeChanceMax) {
        this.atkMin = atkMin;
        this.atkMax = atkMax;
        this.defMin = defMin;
        this.defMax = defMax;
        this.hpMin = hpMin;
        this.hpMax = hpMax;
        this.initiativeMin = initiativeMin;
        this.initiativeMax = initiativeMax;
        this.critChanceMin = critChanceMin;
        this.critChanceMax = critChanceMax;
        this.dodgeChanceMin = dodgeChanceMin;
        this.dodgeChanceMax = dodgeChanceMax;
    }

    public int getAtkMin() {
        return atkMin;
    }

    public int getAtkMax() {
        return atkMax;
    }

    public int getDefMin() {
        return defMin;
    }

    public int getDefMax() {
        return defMax;
    }

    public double getHpMin() {
        return hpMin;
    }

    public double getHpMax() {
        return hpMax;
    }

    public int getInitiativeMin() {
        return initiativeMin;
    }

    public int getInitiativeMax() {
        return initiativeMax;
    }

    public double getCritChanceMin() {
        return critChanceMin;
    }

    public double getCritChanceMax() {
        return critChanceMax;
    }

    public double getDodgeChanceMin() {
        return dodgeChanceMin;
    }

    public double getDodgeChanceMax() {
        return dodgeChanceMax;
    }

}
