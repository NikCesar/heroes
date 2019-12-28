package ch.bfh.swos.arena.model;

import java.util.ArrayList;
import java.util.List;

public class HeroFightStats {

    private int fightsWon;
    private int fightsLost;
    // additional entry whenever hero attacks
    private List<Double> damageCaused;
    // additional entry whenever hero defends oneself
    private List<Double> damageTaken;


    public HeroFightStats(){
        damageCaused = new ArrayList<>();
        damageTaken = new ArrayList<>();
    }

    /* Helper-Methods */

    public void addDamageCaused(double damageCausedInRound){
        damageCaused.add(damageCausedInRound);
    }

    public void addDamageTaken(double damageTakenInRound){
        damageTaken.add(damageTakenInRound);
    }

    public void addWin() { this.fightsWon++; }

    public void addDefeat() {
        this.fightsLost++;
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

    public List<Double> getDamageCaused() {
        return damageCaused;
    }

    public List<Double> getDamageTaken() {
        return damageTaken;
    }
}
