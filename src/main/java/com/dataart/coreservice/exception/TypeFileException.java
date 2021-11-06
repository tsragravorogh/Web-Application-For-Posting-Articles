package com.dataart.coreservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TypeFileException extends RuntimeException{
    public TypeFileException(String message) {
        super(message);
    }
}
