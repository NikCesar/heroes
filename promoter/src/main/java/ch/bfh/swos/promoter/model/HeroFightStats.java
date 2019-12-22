package ch.bfh.swos.promoter.model;

import java.util.ArrayList;
import java.util.List;

public class HeroFightStats {

    private int fightsWon;
    private int fightsLost;

    private List<Double> harmCaused; // additional entry whenever hero attacks
    private List<Double> harmTaken; // additional entry whenever hero defends oneself


    public HeroFightStats(){
        harmCaused = new ArrayList<>();
        harmTaken = new ArrayList<>();
    }

    /* Setters */

    public void setFightsWon(int fightsWon) {
        this.fightsWon = fightsWon;
    }

    public void setFightsLost(int fightsLost) {
        this.fightsLost = fightsLost;
    }

    public void setHarmCaused(List<Double> harmCaused) {
        this.harmCaused = harmCaused;
    }

    public void setHarmTaken(List<Double> harmTaken) {
        this.harmTaken = harmTaken;
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

    public double getAvgHarmCaused(){
        return harmCaused.stream().mapToDouble(val -> val).average().orElse(0.0);
    }

    public double getAvgHarmTaken(){
        return harmTaken.stream().mapToDouble(val -> val).average().orElse(0.0);
    }

    public List<Double> getHarmCaused() { return harmCaused; }

    public List<Double> getHarmTaken() { return harmTaken; }
}
