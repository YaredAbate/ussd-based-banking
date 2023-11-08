package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String RRN;

    private String transactionCode;

    private String accountNumber;

    private String side;

    private double amount;

    private String responseCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    private String phoneNumber;

    public History() {
    }

    public History(String RRN, String transactionCode, String accountNumber,
                   String side, double amount, String responseCode, Date transactionDate, String phoneNumber) {
        this.RRN = RRN;
        this.transactionCode = transactionCode;
        this.accountNumber = accountNumber;
        this.side = side;
        this.amount = amount;
        this.responseCode = responseCode;
        this.transactionDate = transactionDate;
        this.phoneNumber = phoneNumber;
    }
}
