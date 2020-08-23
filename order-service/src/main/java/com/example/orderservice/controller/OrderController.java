package com.example.orderservice.controller;

import com.example.orderservice.dto.CreateOrder;
import com.example.orderservice.dto.ResponseDTO;
import com.example.orderservice.kafka.producer.OrderProducer;
import com.example.orderservice.security.LoggedInUser;
import com.example.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    Logger logger= LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private LoggedInUser loggedInUser;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProducer orderProducer;


    @PostMapping("/order/new")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody CreateOrder newOrder){

        logger.info("New order request from user {}",loggedInUser.getUserId());

        ResponseDTO responseDTO=new ResponseDTO();

        logger.info("calling service method");
        String orderId=orderService.createOrder(newOrder,loggedInUser.getUserId());
        logger.info("created order id {} for request",orderId);


        newOrder.setOrderId(orderId);
        orderProducer.produceOrderRequest(newOrder);


        responseDTO.setPayload("Order Created");

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

}
