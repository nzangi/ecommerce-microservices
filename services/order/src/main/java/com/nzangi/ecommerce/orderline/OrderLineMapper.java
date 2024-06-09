package com.nzangi.ecommerce.orderline;

import com.nzangi.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    // Get OrderLineRequest  to OrderLine
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )

                .productId(request.productId())
                .build();
    }

    // Get OrderLine  to OrderLineResponse
    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
