package com.nzangi.ecommerce.product;

import java.math.BigDecimal;

/**
 *
 * @param productId
 * @param name
 * @param description
 * @param price
 * @param quantity
 */


public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
