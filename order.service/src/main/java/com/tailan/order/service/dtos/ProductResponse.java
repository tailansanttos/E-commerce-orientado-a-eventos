package com.tailan.order.service.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String name, BigDecimal price, Integer quantity) {
}
