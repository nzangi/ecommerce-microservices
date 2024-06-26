package com.nzangi.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

//exception handling
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException{
    private final String message;
}
