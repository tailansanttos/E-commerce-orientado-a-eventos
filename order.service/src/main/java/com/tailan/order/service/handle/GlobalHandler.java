package com.tailan.order.service.handle;

import com.tailan.order.service.dtos.CustomErroResponse;
import com.tailan.order.service.exceptions.OrderNotFoundException;
import com.tailan.order.service.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex){
        CustomErroResponse response = new CustomErroResponse(
                HttpStatus.NOT_FOUND.value(),
                "Produto não encontrado."
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex){
        CustomErroResponse response = new CustomErroResponse(
                HttpStatus.NOT_FOUND.value(),
                "Pedido não encontrado."
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
