package com.nzangi.ecommerce.order;

import com.nzangi.ecommerce.customer.CustomerClient;
import com.nzangi.ecommerce.exception.BusinessException;
import com.nzangi.ecommerce.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    public Integer createOrder(OrderRequest request) {
        //check the customer (OpenFeign)
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot craete order. No customer found found with customer id "+request.customerId()));

        //purchase the products -- > product ms

        //persist the order lines

        //start payment process

        //send notification to notification-ms (Kafka)

        return null;
    }
}
