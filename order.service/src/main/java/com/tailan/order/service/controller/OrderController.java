package com.tailan.order.service.controller;

import com.tailan.order.service.dtos.OrderRequest;
import com.tailan.order.service.dtos.OrderResponse;
import com.tailan.order.service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService  orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        orderService.createNewOrder(orderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("orderId")UUID orderId){
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> orderResponseList = orderService.getAllOrders();
        return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<Void> updatePaymentStatus(@PathVariable("orderId")UUID orderId, @RequestBody @Valid String status){
        orderService.updatePaymentStatus(orderId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
