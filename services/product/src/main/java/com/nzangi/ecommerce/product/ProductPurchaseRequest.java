package com.nzangi.ecommerce.product;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @param productId
 * @param quantity
 */
public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        double quantity
) {
}
