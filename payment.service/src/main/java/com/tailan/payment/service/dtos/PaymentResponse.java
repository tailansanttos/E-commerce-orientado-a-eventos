package com.tailan.payment.service.dtos;

import java.util.UUID;

public record PaymentResponse(UUID orderId, String paymentType, String paymentStatus) {
}
