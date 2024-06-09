package com.nzangi.ecommerce.order;

import org.springframework.stereotype.Service;


/**
 * Order Mapper Class
 */
@Service
public class OrderMapper {
    // Data from OrderRequest to  Order Entity

    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .orderReference(request.orderReference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    // Data from  Order Entity to OrderResponse

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
