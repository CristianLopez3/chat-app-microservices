package com.cristian.msusersservice.exception;

import com.cristian.msusersservice.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(Exception e, HttpServletRequest request) {
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
    public ResponseEntity<String> exception(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

}
