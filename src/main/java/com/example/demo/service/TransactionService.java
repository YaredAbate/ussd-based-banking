package com.example.demo.service;

import com.example.demo.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long transactionId);
    Transaction saveTransaction(Transaction transaction);
    void deleteTransaction(Long transactionId);
}
