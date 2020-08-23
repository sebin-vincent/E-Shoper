package com.example.inventoryservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InventoryException extends RuntimeException{

    private String message;

    private HttpStatus httpStatus;

    public InventoryException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
