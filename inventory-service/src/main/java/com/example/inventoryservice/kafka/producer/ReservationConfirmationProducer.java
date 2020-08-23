package com.example.inventoryservice.kafka.producer;

import com.example.inventoryservice.dto.ReservationConfirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReservationConfirmationProducer {

    Logger logger=LoggerFactory.getLogger(ReservationConfirmationProducer.class);

    private static final String TOPIC="ReservationConfirmation";

    @Autowired
    private KafkaTemplate<String,ReservationConfirmation> kafkaReservationProducer;

    public void confirmReservation(ReservationConfirmation reservationConfirmation){


        logger.info("Sending order confirmation message from kafka");
        kafkaReservationProducer.send(TOPIC,reservationConfirmation);

    }

}
