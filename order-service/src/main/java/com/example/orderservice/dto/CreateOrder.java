package com.example.orderservice.dto;

import com.example.orderservice.model.LineItem;
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

}
