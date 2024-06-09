package com.nzangi.ecommerce.product;

import java.math.BigDecimal;

/**
 *
 * @param id
 * @param name
 * @param description
 * @param availableQuantity
 * @param price
 * @param categoryId
 * @param categoryName
 * @param categoryDescription
 */

public record ProductResponse(
         Integer id,
         String name,
         String description,
         double availableQuantity,
         BigDecimal price,
         Integer categoryId,
         String categoryName,
         String categoryDescription

) {
}
