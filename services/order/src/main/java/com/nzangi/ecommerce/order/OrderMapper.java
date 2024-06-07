package com.nzangi.ecommerce.order;

import org.springframework.stereotype.Service;

@Service

public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .orderReference(request.orderReference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
