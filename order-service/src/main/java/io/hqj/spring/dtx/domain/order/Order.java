package io.hqj.spring.dtx.domain.order;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS", schema = "order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

}
