package com.izaias.valentim.userms.services.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class RoleAlreadyExistException extends RuntimeException{
    public RoleAlreadyExistException(String ex){
        super();
    }
    @Serial
    private static final long serialVersionUID = 1L;
}
