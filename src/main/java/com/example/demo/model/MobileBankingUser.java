package com.example.demo.model;

import com.example.demo.model.enumaration.IsMobileBankingUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    //@OneToOne
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
    //@Column(nullable = false, unique = true)
    //private String CIF;

    @Column(nullable = false, unique = true)
    private String username;

    private String PIN;

    private String password;

    private String phoneNumber;

    private int version;

    private String language;
    @Enumerated(EnumType.STRING)
    private IsMobileBankingUser isMobileBankingUser;

    public MobileBankingUser() {
    }

    public MobileBankingUser(String username, String PIN, String password, String phoneNumber,
                             int version, String language,IsMobileBankingUser isMobileBankingUser) {
        //this.customer = customer;
        //this.CIF = CIF;
        this.username = username;
        this.PIN = PIN;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.version = version;
        this.language = language;
        this.isMobileBankingUser=isMobileBankingUser;
    }
}

