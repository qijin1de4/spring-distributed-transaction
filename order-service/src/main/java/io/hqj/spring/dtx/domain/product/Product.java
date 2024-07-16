package io.hqj.spring.dtx.domain.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS", schema = "order")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private Integer price;

}
