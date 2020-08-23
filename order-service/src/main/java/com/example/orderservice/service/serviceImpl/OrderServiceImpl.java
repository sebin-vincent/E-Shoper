package com.example.orderservice.service.serviceImpl;

import com.example.orderservice.dto.CreateOrder;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    Logger logger=LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String createOrder(CreateOrder createOrder, String userId) {

        logger.info("Creating order model in service class");

        Order order=new Order();

        order.setUserId(userId);
        order.setLineItemList(createOrder.getLineItems());
        orderRepository.save(order);
        order.setStatus(OrderStatus.PENDING);


        orderRepository.save(order);

        logger.info("order created");

        return order.getOrderId();
    }
}
