package com.nzangi.ecommerce.kafka.order;

import java.math.BigDecimal;

// Product details

public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
