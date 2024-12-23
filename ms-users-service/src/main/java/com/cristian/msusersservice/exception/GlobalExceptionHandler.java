package com.cristian.msusersservice.exception;

import com.cristian.msusersservice.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(Exception e, HttpServletRequest request) {
        logger.warn("User not found exception: {}", e.getMessage());
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .localDateTime(LocalDateTime.now()).build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(ResourceDuplicateException.class)
    public ResponseEntity<ErrorResponse> resourceDuplicateException(Exception e, HttpServletRequest request) {
        logger.warn(e.getMessage());
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .localDateTime(LocalDateTime.now()).build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
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


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception e, HttpServletRequest request) {
        logger.error("An error occurred: ", e);
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .localDateTime(LocalDateTime.now()).build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }


}
