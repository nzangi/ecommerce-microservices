package com.nzangi.ecommerce.notification;

import com.nzangi.ecommerce.payment.PaymentMethod;

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

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
