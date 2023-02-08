package com.bootcamp.greencommute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message, Throwable err) {
        super(message, err);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
