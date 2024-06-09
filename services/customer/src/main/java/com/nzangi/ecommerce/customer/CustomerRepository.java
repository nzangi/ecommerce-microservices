package com.nzangi.ecommerce.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// For carrying out database operations
@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
}
