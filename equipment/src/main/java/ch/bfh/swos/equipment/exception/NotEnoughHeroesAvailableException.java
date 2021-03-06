package ch.bfh.swos.equipment.exception;


public class NotEnoughHeroesAvailableException extends Exception {
    public NotEnoughHeroesAvailableException(int requestedNumbOfHeroes, int availNumbOfHeroes){
        super("Not enough heroes available: You requested " + requestedNumbOfHeroes + ", yet there are only " + availNumbOfHeroes + " available.");
    }
}
