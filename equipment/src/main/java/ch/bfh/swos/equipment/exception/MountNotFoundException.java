package ch.bfh.swos.equipment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MountNotFoundException extends Exception {
    public MountNotFoundException(String msg) {
        super(msg);
    }
}
