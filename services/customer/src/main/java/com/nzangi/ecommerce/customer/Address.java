package com.nzangi.ecommerce.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

// This is the class to store the address
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
