package ch.bfh.swos.camp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotEnoughHeroesAvailableException extends Exception {
    public NotEnoughHeroesAvailableException(){
        super();
    }
    public NotEnoughHeroesAvailableException(int requestedNumbOfHeroes, int availNumbOfHeroes){
        super("Not enough heroes available: You requested " + requestedNumbOfHeroes + ", yet there are only " + availNumbOfHeroes + " available.");
    }

}
