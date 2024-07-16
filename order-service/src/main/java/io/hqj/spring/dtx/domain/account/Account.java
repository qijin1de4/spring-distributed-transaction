package io.hqj.spring.dtx.domain.account;

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

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "BALANCE")
    private Integer balance;

}
