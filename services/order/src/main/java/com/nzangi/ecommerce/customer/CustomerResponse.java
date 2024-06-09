package com.nzangi.ecommerce.customer;

/**
 *
 * @param id
 * @param firstname
 * @param lastname
 * @param email
 */
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
