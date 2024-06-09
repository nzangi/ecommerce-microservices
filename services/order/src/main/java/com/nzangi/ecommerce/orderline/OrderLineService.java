package com.nzangi.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Order Line Service
 */
@Service
@RequiredArgsConstructor
public class OrderLineService {
    // Di of orderLineRepository and mapper
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    // save the order to db
    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return orderLineRepository.save(order).getId();
    }

    // List All the Order Line Response
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).
                stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
