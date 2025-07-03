package com.libro_swagger.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse> handleresponseStatusException(ResponseStatusException ex){
        ApiResponse response = new ApiResponse(ex.getReason(), (HttpStatus) ex.getStatusCode());
        return new ResponseEntity<>(response,ex.getStatusCode());

    }
}
