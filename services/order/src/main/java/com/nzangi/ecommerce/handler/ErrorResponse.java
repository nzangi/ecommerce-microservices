package com.nzangi.ecommerce.handler;

import java.util.Map;
// ErrorResponse record
public record ErrorResponse(
        Map<String,String> errors
) {
}
