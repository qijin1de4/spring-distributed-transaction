package io.hqj.spring.dtx.order.domain.account;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PUBTS", nullable = false,
            columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date pubts = new Date();
}
