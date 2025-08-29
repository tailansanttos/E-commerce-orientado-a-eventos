package com.tailan.order.service.service;

import com.tailan.order.service.database.model.Order;
import com.tailan.order.service.dtos.OrderRequest;
import com.tailan.order.service.dtos.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    public void createNewOrder(OrderRequest orderRequest);

    public OrderResponse getOrderById(UUID orderId);
    public List<OrderResponse> getAllOrders();

    public void updatePaymentStatus(UUID orderId, String status);
}
