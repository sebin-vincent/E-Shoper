package com.example.orderservice.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LineItem {

    private String productId;

    private int quantity;

    private float price;

}
