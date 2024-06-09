package com.nzangi.ecommerce.payment;

import java.math.BigDecimal;

/**
 *
 * @param id
 * @param amount
 * @param paymentMethod
 * @param orderId
 * @param orderReference
 * @param customer
 */
public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
