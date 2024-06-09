package com.nzangi.ecommerce.kafka.payment;

import java.math.BigDecimal;

/**
 *
 * @param orderReference
 * @param amount
 * @param paymentMethod
 * @param customerFirstName
 * @param customerLastName
 * @param customerEmail
 */
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
        ) {
}
