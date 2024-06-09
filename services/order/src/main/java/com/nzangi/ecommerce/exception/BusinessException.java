package com.nzangi.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

// Business Exception Class
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException{
    private final String message;
}
