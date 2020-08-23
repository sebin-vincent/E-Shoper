package com.example.Authserver.Exception;

import org.springframework.http.HttpStatus;

public class SecurityException extends RuntimeException{

    private HttpStatus httpStatus;

    private String message;

    public SecurityException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatus=httpStatus;
    }
}
