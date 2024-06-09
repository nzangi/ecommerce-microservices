package com.nzangi.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

// Request data for customer which will be saved on customer db

/**
 *
 * @param id
 * @param firstname
 * @param lastname
 * @param email
 * @param address
 */
public record CustomerRequest(
         String id,
         @NotNull(message = "customer firstname is required")
         String firstname,
         @NotNull(message = "customer lastname is required")
         String lastname,
         @NotNull(message = "customer email is required")
         @Email(message = "customer email must be valid!")
         String email,
         Address address
) {
}
