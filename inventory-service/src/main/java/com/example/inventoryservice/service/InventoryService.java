package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.AddProduct;
import com.example.inventoryservice.dto.CreateOrder;
import com.example.inventoryservice.dto.LineItem;

import java.util.List;

public interface InventoryService {

    boolean reserveLineItems(CreateOrder order);

    void saveProduct(AddProduct product);
}
