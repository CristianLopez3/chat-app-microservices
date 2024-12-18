package com.cristian.msconversationsservice.exception;


import com.cristian.msconversationsservice.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

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


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e, HttpServletRequest request) {
        getError(e, request);
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    private static void getError(Exception e, HttpServletRequest request) {
        logger.error("path: {}, message: {}", request.getRequestURI(), e.getMessage());
    }

}
