package com.nzangi.ecommerce.payment;

import com.nzangi.ecommerce.customer.CustomerResponse;
import com.nzangi.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
