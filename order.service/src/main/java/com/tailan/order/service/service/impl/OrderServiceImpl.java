package com.tailan.order.service.service.impl;

import com.tailan.order.service.database.model.Order;
import com.tailan.order.service.database.model.Product;
import com.tailan.order.service.database.repository.OrderRepository;
import com.tailan.order.service.dtos.OrderRequest;
import com.tailan.order.service.dtos.OrderResponse;
import com.tailan.order.service.exceptions.OrderNotFoundException;
import com.tailan.order.service.infra.KafkaProducer;
import com.tailan.order.service.service.OrderService;
import com.tailan.order.service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService  productService;
    private final KafkaProducer kafkaProducer;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, KafkaProducer kafkaProducer) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void createNewOrder(OrderRequest orderRequest) {
        Product product = productService.getProductById(orderRequest.productId());
        Order order = new Order(orderRequest, product);
        Order savedOrder = orderRepository.save(order);

        OrderResponse response = new OrderResponse(
                savedOrder.getId(),
                product,
                savedOrder.getQuantity(),
                savedOrder.getValue(),
                savedOrder.getOrderTime(),
                savedOrder.getPaymentStatus()
        );

        this.sendMessageToKafka(response);

    }

    @Override
    public OrderResponse getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado."));
        Product product = productService.getProductById(order.getProductId());
        return new OrderResponse(
            order.getId(),
            product,
            order.getQuantity(),
            order.getValue(),
            order.getOrderTime(),
            order.getPaymentStatus()
        );
    }

    private void sendMessageToKafka(OrderResponse orderResponse) {
        kafkaProducer.send(orderResponse);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order ->
                new OrderResponse(order.getId(),
                productService.getProductById(order.getProductId()),
                order.getQuantity(),
                order.getValue(),
                order.getOrderTime(),
                        order.getPaymentStatus()
        )).toList();
    }

    @Override
    public void updatePaymentStatus(UUID orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado."));
        order.setPaymentStatus(status);
        orderRepository.save(order);
    }
}
