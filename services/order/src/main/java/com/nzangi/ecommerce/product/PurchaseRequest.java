package com.nzangi.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @param productId
 * @param quantity
 */
public record PurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Integer productId,
    @Positive(message = "Quantity is mandatory")
    double quantity
) {
}
