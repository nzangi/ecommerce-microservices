package com.nzangi.ecommerce.customer;

import com.nzangi.ecommerce.exception.CustomerNotFoundException;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
/*
* Customer Service
* */
@Service
@RequiredArgsConstructor
public class CustomerService {
    // DI of customerRepository and customerMapper
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    // create customer service and save to db
    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    // update customer on mongo db
    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).orElseThrow(
                () -> new CustomerNotFoundException(
                        "Cannot update customer with the provided id "+request.id()
                )
        );
        mergeCustomer(customer,request);
        customerRepository.save(customer);
    }
    // check if CustomerRequest are null before updating
    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }
   // Get all customer from the customer document service
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    // check if customer exists by id service
    public Boolean customerExistsById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    // find customer by id service
    public CustomerResponse findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot update customer with the provided id "+customerId

                ));

    }

    // delete customer service
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
