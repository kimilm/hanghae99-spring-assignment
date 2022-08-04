package com.kimilm.expert.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleException(IllegalArgumentException ex) {
        // 400 BadRequest
        return ResponseEntity.badRequest().body(RestApiException.of(ex.getMessage()));
    }
}
