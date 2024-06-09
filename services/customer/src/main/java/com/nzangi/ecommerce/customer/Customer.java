package com.nzangi.ecommerce.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 /*
 * Customer Model Document for Mongo DB
 * */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document
public class Customer {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;

}
