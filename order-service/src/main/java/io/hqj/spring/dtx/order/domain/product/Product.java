package io.hqj.spring.dtx.order.domain.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS", schema = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "code", unique = true)
    private Long code;

}
