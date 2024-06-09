package com.nzangi.ecommerce.kafka;

import com.nzangi.ecommerce.customer.CustomerResponse;
import com.nzangi.ecommerce.order.PaymentMethod;
import com.nzangi.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

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
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
