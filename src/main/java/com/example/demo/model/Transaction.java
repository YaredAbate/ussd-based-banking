package com.example.demo.model;

import com.example.demo.model.enumaration.TransactionStatus;
import com.example.demo.model.enumaration.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private String otp;
    private String responseCode;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private LocalDateTime transactionDate;

    public Transaction() {
    }

    public Transaction(String RRN, String transactionCode, String accountNum, String side, double amount, String otp, String responseCode,
                       TransactionType transactionType, TransactionStatus status, LocalDateTime transactionDate) {
        this.RRN = RRN;
        this.transactionCode = transactionCode;
        this.accountNum = accountNum;
        this.side = side;
        this.amount = amount;
        this.otp = otp;
        this.responseCode = responseCode;
        this.transactionType = transactionType;
        this.status = status;
        this.transactionDate = transactionDate;
    }
}

