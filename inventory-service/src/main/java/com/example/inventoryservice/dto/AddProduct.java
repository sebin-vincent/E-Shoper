package com.example.inventoryservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor
public class AddProduct {

    private String productId;

    @NotNull
    private String productName;

    private int quantity;

    private float price;

}
