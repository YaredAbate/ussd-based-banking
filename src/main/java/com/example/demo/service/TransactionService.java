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
    String transferFunds(String fromAccountNumber, String toAccountNumber, double amount);

    String initiateCashDeposit(String accountNumber, double amount);
    String completeCashDeposit(String otp,String accountNumber);
    String merchantCashWithdrawal(String accountNumber, double amount);
    String merchantCashDeposit(String accountNumber,double amount);
    String initiateCashWithdrawal(String accountNumber, double amount);
    void completeCashWithdrawal(String otp,String accountNumber);
    List<Transaction> getRecentTransactions(String AccountNumber);
}
