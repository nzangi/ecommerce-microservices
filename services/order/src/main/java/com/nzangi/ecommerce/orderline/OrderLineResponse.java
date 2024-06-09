package com.nzangi.ecommerce.orderline;

/**
 * 
 * @param id
 * @param quantity
 */
public record OrderLineResponse(
        Integer id,
        double quantity
) {
}
