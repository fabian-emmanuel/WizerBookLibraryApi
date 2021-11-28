package com.codewithfibbee.wizerbooklibraryapi.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String message, HttpStatus userAlreadyExists){
        super(message);
    }

    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
