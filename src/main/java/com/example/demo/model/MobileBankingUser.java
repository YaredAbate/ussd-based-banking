package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class MobileBankingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship with Customer
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false, unique = true)
    private String CIF;

    @Column(nullable = false, unique = true)
    private String username;

    private String PIN;

    private String password;

    private String phoneNumber;

    private int version;

    private String language;

    public MobileBankingUser() {
    }

    public MobileBankingUser(Customer customer, String CIF,
                             String username, String PIN, String password, String phoneNumber,
                             int version, String language) {
        this.customer = customer;
        this.CIF = CIF;
        this.username = username;
        this.PIN = PIN;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.version = version;
        this.language = language;
    }
}

