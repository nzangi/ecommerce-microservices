package com.nzangi.ecommerce.order;

import com.nzangi.ecommerce.customer.CustomerClient;
import com.nzangi.ecommerce.exception.BusinessException;
import com.nzangi.ecommerce.kafka.OrderConfirmation;
import com.nzangi.ecommerce.kafka.OrderProducer;
import com.nzangi.ecommerce.orderline.OrderLineService;
import com.nzangi.ecommerce.orderline.OrderLineRequest;

import com.nzangi.ecommerce.payment.PaymentClient;
import com.nzangi.ecommerce.payment.PaymentRequest;
import com.nzangi.ecommerce.product.ProductClient;
import com.nzangi.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Order Service
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;



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

        // start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getOrderReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);


        //send notification to notification-ms (Kafka)

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts

                )
        );

        return order.getId();
    }

    // find all orders
    public List<OrderResponse> findAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOder)
                .collect(Collectors.toList());
    }

    // find order by Id
    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOder)
                .orElseThrow(() -> new EntityNotFoundException("The oder Id "+orderId+" was not found"));
    }
}
