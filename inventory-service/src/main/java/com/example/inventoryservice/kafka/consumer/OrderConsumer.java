package com.example.inventoryservice.kafka.consumer;

import com.example.inventoryservice.dto.CreateOrder;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.service.InventoryService;
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
public class OrderConsumer {

    private Logger logger =LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private InventoryService inventoryService;


    @KafkaListener(topics = "OrderRequest",groupId = "group_id",containerFactory = "kafkaListenerContainerFactory")
    public void consumeOrderRequest(String stringOrder) throws JsonProcessingException {

        JSONObject jsonObject=new JSONObject();
        ObjectMapper mapper=new ObjectMapper();

        CreateOrder createOrder=null;

        try {
            jsonObject = new JSONObject(stringOrder);
        }catch (JSONException e){
            logger.error(e.getMessage());
        }

        createOrder=mapper.readValue(stringOrder,CreateOrder.class);

        logger.info("Received reservation request in kafka listner for orderId {}",createOrder.getOrderId());

        inventoryService.reserveLineItems(createOrder);

    }

}
