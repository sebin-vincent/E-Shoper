package com.example.inventoryservice.exception;

import com.example.inventoryservice.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
@RestController
public class InventoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InventoryException.class)
    public final ResponseEntity<ResponseDTO> handleDataNotFoundException(InventoryException ex, WebRequest request) {
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setStatus(ex.getHttpStatus().value());
        return new ResponseEntity<>(responseDTO, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ResponseDTO> handleArgumentMismatching(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        responseDTO.setPayload(ex.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }


}
