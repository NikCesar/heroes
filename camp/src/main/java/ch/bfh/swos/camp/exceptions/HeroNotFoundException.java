package ch.bfh.swos.camp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HeroNotFoundException extends Exception {
    public HeroNotFoundException(String msg){
        super(msg);
    }
}
