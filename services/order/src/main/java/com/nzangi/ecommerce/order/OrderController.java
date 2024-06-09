package com.nzangi.ecommerce.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Order Controller Class
 * */
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    // DI of orderService
    private final OrderService orderService;

    // create order controller
    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request
    ){
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    // find all orders controller
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(){
        return ResponseEntity.ok(orderService.findAllOrders());

    }

    // find by id controller
    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId
    ){
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }

}
