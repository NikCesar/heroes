package ch.bfh.swos.camp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotEnoughHeroesAvailableException extends Exception {

    public NotEnoughHeroesAvailableException(int requestedNumbOfHeroes, int availNumbOfHeroes){
        super("Not enough heroes available: Your requested " + requestedNumbOfHeroes + ", yet there are currently just " + availNumbOfHeroes + " available.");
    }

}
