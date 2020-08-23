package com.example.orderservice.kafka.producer;

import com.example.orderservice.dto.CreateOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    Logger logger=LoggerFactory.getLogger(OrderProducer.class);

    private static final String TOPIC="OrderRequest";

    @Autowired
    private KafkaTemplate<String, CreateOrder> kafkaTemplate;

    public void produceOrderRequest(CreateOrder createOrder){

        logger.info("Sending kafka message for inventory reservation for orderId {}",createOrder.getOrderId());
        kafkaTemplate.send(TOPIC,createOrder);
        logger.info("Kafka message for inventory reservation for orderId {} have been sent",createOrder.getOrderId());

    }

}
