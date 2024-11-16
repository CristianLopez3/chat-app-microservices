package com.cristian.msgatewayservice.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ErrorResponse handleNoResourceFoundException(NoResourceFoundException e ) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .statusCode(e.getStatusCode().value())
                .localDateTime(LocalDateTime.now())
                .build();
    }


}
