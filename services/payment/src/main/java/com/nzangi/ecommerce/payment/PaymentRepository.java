package com.nzangi.ecommerce.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Payment Repository
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
