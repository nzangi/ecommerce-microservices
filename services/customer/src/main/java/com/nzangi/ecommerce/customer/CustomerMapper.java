package com.nzangi.ecommerce.customer;

import org.springframework.stereotype.Service;
// Customer mapper
@Service
public class CustomerMapper {
    // From Request CustomerRequest  to Customer Entity:
    public Customer toCustomer(CustomerRequest request) {
        if(request == null){
            return null;
        }

        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }


    // From Request Customer Entity CustomerResponse:
    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
