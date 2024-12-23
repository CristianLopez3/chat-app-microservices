package com.cristian.msconversationsservice.exception;

import com.cristian.msconversationsservice.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(Exception e, HttpServletRequest request) {
        getError(e, request);
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .localDateTime(LocalDateTime.now()).build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(Exception e, HttpServletRequest request) {
        getError(e, request);
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .localDateTime(LocalDateTime.now()).build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        var errorMessages = e.getBindingResult()
                .getFieldErrors().stream()
                .map(error -> String.format("Field: %s, %s (rejected value: %s)",
                        error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue()))
                .collect(Collectors.joining(", "));

        logger.warn("Method argument not valid exception: {}", e.getMessage());
        var errorResponse = ErrorResponse.builder()
                .message(errorMessages)
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .localDateTime(LocalDateTime.now()).build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    ResponseEntity<ErrorResponse> handlerMethodValidationException(HandlerMethodValidationException ex, HttpServletRequest request) {
        logger.warn("{}", ex.getClass());
        String description = Arrays.toString(ex.getDetailMessageArguments());

        var errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .localDateTime(LocalDateTime.now())
                .message(description)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        getError(e, request);
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .localDateTime(LocalDateTime.now()).build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e, HttpServletRequest request) {
        getError(e, request);
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    private static void getError(Exception e, HttpServletRequest request) {
        logger.warn("path: {}, message: {}", request.getRequestURI(), e.getMessage());
    }

}
