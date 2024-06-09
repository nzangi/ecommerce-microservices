package com.nzangi.ecommerce.handler;

import java.util.Map;

/**
 *
 * @param errors
 */
public record ErrorResponse(
        Map<String,String> errors
) {


}
