package com.cristian.msgatewayservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(NoResourceFoundException.class)
    public ErrorResponse handleNoResourceFoundException(NoResourceFoundException e ) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .statusCode(e.getStatusCode().value())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse exception(Exception e, ServerWebExchange exchange) {
        logger.warning(e.getMessage());
        return ErrorResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .localDateTime(LocalDateTime.now())
                .path(exchange.getRequest().getPath().toString())
                .build();
    }


}
