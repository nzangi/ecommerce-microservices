package com.nzangi.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Database operations

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
