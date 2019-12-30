package ch.bfh.swos.camp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidHeroException extends Exception {
    public InvalidHeroException(String msg){
        super(msg);
    }
}
