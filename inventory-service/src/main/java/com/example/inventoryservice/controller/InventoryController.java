package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.AddProduct;
import com.example.inventoryservice.dto.LineItem;
import com.example.inventoryservice.dto.ResponseDTO;
import com.example.inventoryservice.security.LoggedInUser;
import com.example.inventoryservice.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    Logger logger= LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private LoggedInUser loggedInUser;


    @PostMapping("/product")
    public ResponseEntity<ResponseDTO> createProduct(@RequestBody AddProduct product){


        logger.info("Request from {} to Add new Product",loggedInUser.getUserId());

        inventoryService.saveProduct(product);

        ResponseDTO responseDTO=new ResponseDTO();

        responseDTO.setStatus(200);
        responseDTO.setPayload("Product added to inventory");

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}
