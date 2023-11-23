package com.example.demo.service;

import com.example.demo.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long accountId);
    Account saveAccount(Account account);
    Account updateAccount1(long id,Account account);
    void deleteAccount(Long accountId);
    Account getAccountByAccountNumber(String accountNumber); // New method
    void updateAccount(Account account);
    double getBalanceByAccountNumber(String accountNumber);
}