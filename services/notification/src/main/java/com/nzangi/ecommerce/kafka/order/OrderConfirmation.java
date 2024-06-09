package com.nzangi.ecommerce.kafka.order;

import com.nzangi.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

// Order Confirmation details

/**
 *
 * @param orderReference
 * @param totalAmount
 * @param paymentMethod
 * @param customer
 * @param products
 */
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
