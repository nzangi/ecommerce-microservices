package com.nzangi.ecommerce.order;

import com.nzangi.ecommerce.customer.CustomerClient;
import com.nzangi.ecommerce.exception.BusinessException;
import com.nzangi.ecommerce.kafka.OrderConfirmation;
import com.nzangi.ecommerce.kafka.OrderProducer;
import com.nzangi.ecommerce.orderline.OrderLineService;
import com.nzangi.ecommerce.orderline.OrderLineRequest;

import com.nzangi.ecommerce.product.ProductClient;
import com.nzangi.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public Integer createOrder(OrderRequest request) {
        //check the customer (OpenFeign)
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order. No customer found found with customer id "+request.customerId()));

        //purchase the products -- > product ms

        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        //persist the order

        var order = orderRepository.save(mapper.toOrder(request));

        //persist the order lines

        for (PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        // todo start payment process


        //send notification to notification-ms (Kafka)

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts

                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOder)
                .collect(Collectors.toList());
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOder)
                .orElseThrow(() -> new EntityNotFoundException("The oder Id "+orderId+" was not found"));
    }
}
