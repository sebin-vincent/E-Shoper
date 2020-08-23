package com.example.profileservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ResponseDTO {

    private int status=200;
    private String message;
    private Object payload;

    public ResponseDTO(int status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }
}
