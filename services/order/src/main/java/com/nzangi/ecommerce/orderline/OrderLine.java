package com.nzangi.ecommerce.orderline;

import com.nzangi.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;

/**
 * OrderLine Entity
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customer_line")
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;
}
