package ch.bfh.swos.equipment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ArmorNotFoundException extends Exception {
    public ArmorNotFoundException(String msg) {
        super(msg);
    }
}
