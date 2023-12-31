package com.example.demo.model;
/*
*
*
* */
import com.example.demo.model.enumaration.AccountStatus;
import com.example.demo.model.enumaration.CustomerType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Column(nullable = false)
    private Date createdAt;

    private Date expiredAt;


    @ManyToOne
    @JoinColumn(name = "cif_id")
    private Customer customer;
    public Account() {
    }

    public Account(String accountNumber, double balance, AccountStatus accountStatus,CustomerType customerType,
                   Date createdAt, Date expiredAt) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountStatus = accountStatus;
        this.customerType=customerType;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        //this.customer = customer;
    }


// Constructors, getters, and setters
}
