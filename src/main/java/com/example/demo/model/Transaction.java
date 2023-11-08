package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String RRN;

    private String transactionCode;

    private String accountNum;

    private String side;

    private double amount;

    private String responseCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    public Transaction() {
    }

    public Transaction(String RRN, String transactionCode,
                       String accountNum, String side, double amount, String responseCode, Date transactionDate) {
        this.RRN = RRN;
        this.transactionCode = transactionCode;
        this.accountNum = accountNum;
        this.side = side;
        this.amount = amount;
        this.responseCode = responseCode;
        this.transactionDate = transactionDate;
    }
}

