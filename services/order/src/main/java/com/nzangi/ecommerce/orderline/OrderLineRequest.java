package com.nzangi.ecommerce.orderline;

/**
 *
 * @param id
 * @param orderId
 * @param productId
 * @param quantity
 */
public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}
