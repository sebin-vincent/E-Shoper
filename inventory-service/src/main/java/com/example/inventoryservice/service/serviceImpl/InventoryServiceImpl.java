package com.example.inventoryservice.service.serviceImpl;

import com.example.inventoryservice.dto.AddProduct;
import com.example.inventoryservice.dto.CreateOrder;
import com.example.inventoryservice.dto.LineItem;
import com.example.inventoryservice.dto.ReservationConfirmation;
import com.example.inventoryservice.exception.InventoryException;
import com.example.inventoryservice.kafka.producer.ReservationConfirmationProducer;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    Logger logger= LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ReservationConfirmationProducer reservationConfirmationProducer;

    @Override
    public boolean reserveLineItems(CreateOrder order) {

        logger.info("Processing reservation request for orderID {}",order.getOrderId());

        Inventory inventory;

        List<LineItem> lineItems=order.getLineItems();

        for(LineItem lineItem: lineItems){

            inventory=inventoryRepository.findById(lineItem.getProductId()).get();
            inventory.setQuantity(inventory.getQuantity()-lineItem.getQuantity());
            inventoryRepository.save(inventory);


        }
        ReservationConfirmation reservationConfirmation=new ReservationConfirmation();
        reservationConfirmation.setOrderId(order.getOrderId());
        reservationConfirmation.setOrderCreated(true);

        logger.info("Processing reservation request for orderID {}",order.getOrderId());

        reservationConfirmationProducer.confirmReservation(reservationConfirmation);

        return true;
    }

    @Override
    public void saveProduct(AddProduct product) {

        validate(product);

        logger.info("Processing request for adding new product");

        Inventory inventory=new Inventory();

        inventory.setProductName(product.getProductName());
        inventory.setQuantity(product.getQuantity());
        inventory.setPrice(product.getPrice());

        logger.info("Processed request for adding new product product_Id {}",inventory.getProductId());


        inventoryRepository.save(inventory);

    }

    private void validate(AddProduct product) throws InventoryException{

        if(product.getPrice()<0) throw new InventoryException("Price can't negative", HttpStatus.BAD_REQUEST);

        if (product.getQuantity()<0) throw new InventoryException("Price can't negative", HttpStatus.BAD_REQUEST);

    }
}
