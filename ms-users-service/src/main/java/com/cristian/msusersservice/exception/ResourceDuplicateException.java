package com.cristian.msusersservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceDuplicateException extends RuntimeException{
    public ResourceDuplicateException(String message){
        super(message);
    }
}