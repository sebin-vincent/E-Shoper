package com.example.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ResponseDTO {

    private int status=200;

    private String message;

    private Object payload;
}
