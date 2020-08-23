package com.example.inventoryservice.service.serviceImpl;

import com.example.inventoryservice.dto.AddProduct;
import com.example.inventoryservice.exception.InventoryException;
import com.example.inventoryservice.kafka.producer.ReservationConfirmationProducer;
import com.example.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    private ReservationConfirmationProducer reservationConfirmationProducer;

    @Mock
    private InventoryRepository inventoryRepository;


    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    public void test_SaveProduct_withNegativeQuantity(){


        AddProduct addProduct=new AddProduct();
        addProduct.setProductName("product_with_negative_quantity");
        addProduct.setQuantity(-5);
        addProduct.setPrice(150);
        assertThrows(InventoryException.class,()-> inventoryService.saveProduct(addProduct));

    }

    @Test
    public void test_SaveProduct_withNegativePrice(){


        AddProduct addProduct=new AddProduct();
        addProduct.setProductName("product_with_negative_price");
        addProduct.setQuantity(150);
        addProduct.setPrice(-37);
        assertThrows(InventoryException.class,()-> inventoryService.saveProduct(addProduct));

    }

}