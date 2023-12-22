package com.izaias.valentim.userms.services.customExceptions;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String ex) {
        super(ex);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
