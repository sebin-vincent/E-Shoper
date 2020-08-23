package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
@Getter @Setter @NoArgsConstructor
public class Inventory {

    @Id
    private String productId;

    private String productName;

    private int quantity;

    private float price;

}
