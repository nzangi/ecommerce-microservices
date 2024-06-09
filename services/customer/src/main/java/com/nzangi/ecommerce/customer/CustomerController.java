package com.nzangi.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
* Customer Controller
* */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    //DI of customerService
    private final CustomerService customerService;

    // create a customer and save on mongo db controller
    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    // update the customer details on the mongo db
    @PutMapping
    public  ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    // find all customers registered on mongo db
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    // check if customer exist on db
    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> customerExistsById(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(customerService.customerExistsById(customerId));
    }

    // Get customer by Id
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomerById(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    // Delete customer on db
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("customer-id") String customerId
    ){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }



}
