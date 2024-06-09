package com.nzangi.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
//CustomerResponse from Customer Collection (Table) to User

/**
 *
 * @param id
 * @param firstname
 * @param lastname
 * @param email
 * @param address
 */
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
