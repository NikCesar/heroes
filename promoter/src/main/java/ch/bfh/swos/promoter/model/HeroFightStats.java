package ch.bfh.swos.promoter.model;

import java.util.ArrayList;
import java.util.List;

public class HeroFightStats {

    private int fightsWon;
    private int fightsLost;

    private List<Double> damageCaused; // additional entry whenever hero attacks
    private List<Double> damageTaken; // additional entry whenever hero defends oneself


    public HeroFightStats(){
        damageCaused = new ArrayList<>();
        damageTaken = new ArrayList<>();
    }

    /* Setters */

    public void setFightsWon(int fightsWon) {
        this.fightsWon = fightsWon;
    }

    public void setFightsLost(int fightsLost) {
        this.fightsLost = fightsLost;
    }

    public void setDamageCaused(List<Double> damageCaused) {
        this.damageCaused = damageCaused;
    }

    public void setDamageTaken(List<Double> damageTaken) {
        this.damageTaken = damageTaken;
    }


    /* Getters */

    public int getFightsWon() {
        return fightsWon;
    }

    public int getFightsLost() {
        return fightsLost;
    }

    public int getFightsTotal() {
        return fightsWon + fightsLost;
    }

    public double getAvgDamageCaused(){
        return damageCaused.stream().mapToDouble(val -> val).average().orElse(0.0);
    }

    public double getAvgDamageTaken(){
        return damageTaken.stream().mapToDouble(val -> val).average().orElse(0.0);
    }

    public List<Double> getDamageCaused() { return damageCaused; }

    public List<Double> getDamageTaken() { return damageTaken; }
}
