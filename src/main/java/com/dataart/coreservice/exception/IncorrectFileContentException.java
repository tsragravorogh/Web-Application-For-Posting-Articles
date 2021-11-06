package com.dataart.coreservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class IncorrectFileContentException extends RuntimeException{
    public IncorrectFileContentException(String message) {
        super(message);
    }
}
