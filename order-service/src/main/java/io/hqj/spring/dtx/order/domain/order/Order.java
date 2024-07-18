package io.hqj.spring.dtx.order.domain.order;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PUBTS", nullable = false,
            columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date pubts = new Date();

}
