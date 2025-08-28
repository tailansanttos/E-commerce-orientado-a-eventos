package com.tailan.order.service.database.model;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tailan.order.service.dtos.OrderRequest;
import com.tailan.order.service.enums.PaymentStatusEnum;
import com.tailan.order.service.enums.PaymentTypeEnum;
import org.bson.json.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "orders")
public class Order {
    @Id
    private UUID id;
    private UUID productId;
    private Integer quantity;
    private BigDecimal value;
    private LocalDateTime orderTime;
    private String paymentStatus;
    private String paymentType;

    public Order() {
    }

    public Order(OrderRequest orderRequest, Product product){
        this.id = UUID.randomUUID();
        this.productId = product.getId();
        this.quantity = orderRequest.quantity();
        this.orderTime = LocalDateTime.now();
        this.value = product.getPrice().multiply(new BigDecimal(orderRequest.quantity()));
        this.paymentStatus = PaymentStatusEnum.PENDING.name().toUpperCase();
        this.paymentType = orderRequest.paymentType().name().toUpperCase();
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
