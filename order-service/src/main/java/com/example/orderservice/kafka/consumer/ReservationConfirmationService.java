package com.example.orderservice.kafka.consumer;

import com.example.orderservice.dto.ReservationConfirmation;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReservationConfirmationService{

    Logger logger=LoggerFactory.getLogger(ReservationConfirmationService.class);

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "ReservationConfirmation", groupId = "RESERVATION_GROUP",containerFactory = "reservationListenerFactory")
    public void changeOrderStatus(String confirmationObject) throws JSONException, JsonProcessingException {


        JSONObject jsonObject=new JSONObject(confirmationObject);

        ObjectMapper mapper=new ObjectMapper();

        ReservationConfirmation confirmation=mapper.readValue(jsonObject.toString(),ReservationConfirmation.class);

        logger.info("Order Confirmation request from Inventory for orderId {}",confirmation.getOrderId());

        Order order=orderRepository.findById(confirmation.getOrderId()).get();

        order.setStatus(OrderStatus.ACCEPTED);

        orderRepository.save(order);


    }


}
