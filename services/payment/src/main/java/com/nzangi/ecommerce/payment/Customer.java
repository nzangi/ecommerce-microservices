package com.nzangi.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @param id
 * @param firstname
 * @param lastname
 * @param email
 */

@Validated
public record Customer(
        String id,
        @NotNull(message = "Firstname is required")
        String firstname,
        @NotNull(message = "Lastname is required")
        String lastname,
        @NotNull(message = "E-mail is required")
        @Email(message = "Enter a valid E-mail username")
        String email
) {
}
