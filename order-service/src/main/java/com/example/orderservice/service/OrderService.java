package com.example.orderservice.service;

import com.example.orderservice.dto.CreateOrder;
import com.example.orderservice.model.LineItem;

import java.util.List;

public interface OrderService {

    String createOrder(CreateOrder createOrder, String userId);
}
