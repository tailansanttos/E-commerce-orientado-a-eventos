package com.tailan.order.service.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        @NotNull
        String name,
        @NotNull
        BigDecimal price,
        @NotNull
        Integer quantity) {
}
