package com.example.inventoryservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class CreateOrder implements Serializable{



    private String orderId;

    private List<LineItem> lineItems=new ArrayList<>();

    @Override
    public String toString() {
        return "CreateOrder{" +
                "orderId='" + orderId + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }
}
