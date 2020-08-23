package com.example.orderservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "order")
@Getter @Setter @NoArgsConstructor
public class Order {

    @Id
    private String orderId;

    private String userId;

    private List<LineItem> lineItemList=new ArrayList<>();

    private OrderStatus status;


}
