package com.example.demo.service;

import com.example.demo.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long transactionId);
    Transaction saveTransaction(Transaction transaction);
    Transaction updateTransaction(Long id,Transaction transaction);
    void deleteTransaction(Long transactionId);
}
