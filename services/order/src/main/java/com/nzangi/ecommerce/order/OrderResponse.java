package com.nzangi.ecommerce.order;

import java.math.BigDecimal;

/**
 *
 * @param id
 * @param orderReference
 * @param amount
 * @param paymentMethod
 * @param customerId
 */
public record OrderResponse(
        Integer id,
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
