package com.nzangi.ecommerce.handler;

import java.util.Map;

// error record
public record ErrorResponse(
        Map<String,String> errors
) {


}
