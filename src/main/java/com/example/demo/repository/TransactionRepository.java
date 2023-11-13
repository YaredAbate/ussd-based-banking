package com.example.demo.repository;

import com.example.demo.model.Transaction;
import com.example.demo.model.enumaration.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Transaction findByOtpAndStatus(String otp, TransactionStatus status);
}
