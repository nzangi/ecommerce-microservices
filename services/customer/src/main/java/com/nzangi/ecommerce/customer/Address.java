package com.nzangi.ecommerce.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

//
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
