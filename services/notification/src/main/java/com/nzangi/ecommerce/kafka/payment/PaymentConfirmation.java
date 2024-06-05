package com.nzangi.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String oderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
        ) {
}
