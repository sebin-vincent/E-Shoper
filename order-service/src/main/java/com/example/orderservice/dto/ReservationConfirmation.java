package com.example.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
public class ReservationConfirmation implements Serializable {

    private String orderId;

    private boolean orderCreated;

    public ReservationConfirmation(String orderId, boolean orderCreated) {
        this.orderId = orderId;
        this.orderCreated = orderCreated;
    }

    @Override
    public String toString() {
        return "ReservationConfirmation{" +
                "orderId='" + orderId + '\'' +
                ", orderCreated=" + orderCreated +
                '}';
    }
}