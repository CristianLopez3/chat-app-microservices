package com.cristian.messages.exception;

import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException ex, ServerWebExchange exchange) {
        logger.warn("Resource not found: {}", ex.getMessage());
        var errorResponse = ErrorResponse.builder()
                .path(exchange.getRequest().getPath().value())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationExceptions(MethodArgumentNotValidException ex, ServerWebExchange exchange) {
        var errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        logger.warn("Validation error: {}", errorMessage);
        var errorResponse = ErrorResponse.builder()
                .path(exchange.getRequest().getPath().value())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .localDateTime(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex, ServerWebExchange exchange) {
        logger.warn("Exception: {}", ex.getMessage());
        var errorResponse = ErrorResponse.builder()
                .path(exchange.getRequest().getPath().value())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
