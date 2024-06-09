package com.nzangi.ecommerce.kafka.order;



// Customer Record for Kafka
public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
