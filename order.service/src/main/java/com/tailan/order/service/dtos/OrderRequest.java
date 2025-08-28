package com.tailan.order.service.dtos;

import com.tailan.order.service.enums.PaymentTypeEnum;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderRequest(@NotNull
                           UUID productId,
                           @NotNull
                           Integer quantity,
                           @NotNull
                           PaymentTypeEnum paymentType) {
}
