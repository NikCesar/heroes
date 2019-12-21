package ch.bfh.swos.camp.model;

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

    /* Helper-Methods */

    public void addHarmCaused(double harmCausedInRound){
        harmCaused.add(harmCausedInRound);
    }

    public void addHarmTaken(double harmTakenInRound){
        harmTaken.add(harmTakenInRound);
    }

    public void addWin() {
        this.fightsWon++;
    }

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

    public List<Double> getHarmCaused() {
        return harmCaused;
    }

    public List<Double> getHarmTaken() {
        return harmTaken;
    }
}
