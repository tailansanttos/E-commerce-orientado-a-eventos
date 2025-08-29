package com.tailan.order.service.dtos;

import com.tailan.order.service.database.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(UUID id,
                            Product product,
                            Integer quantity,
                            BigDecimal value,
                            LocalDateTime orderTime,
                            String paymentStatus) {
}
