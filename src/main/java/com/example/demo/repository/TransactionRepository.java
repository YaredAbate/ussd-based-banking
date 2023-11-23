package com.example.demo.repository;

import com.example.demo.model.Transaction;
import com.example.demo.model.enumaration.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Transaction findByOtpAndStatus(String otp, TransactionStatus status);
    List<Transaction> findTop5ByAccountNumOrderByTransactionDateDesc(String accountNumber);
}
