package io.hqj.spring.dtx.order.domain.account;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACCOUNTS", schema = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUSTOMER_ID", nullable = false, unique = true)
    private Long customerId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BALANCE", nullable = false)
    private Integer balance;
}
