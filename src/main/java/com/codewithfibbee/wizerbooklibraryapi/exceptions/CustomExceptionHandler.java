package com.codewithfibbee.wizerbooklibraryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(exception.getMessage(), status);
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsException(ResourceAlreadyExistsException exception){
        HttpStatus status = HttpStatus.CONFLICT;
        return new ResponseEntity<>(exception.getMessage(), status);
    }
}

