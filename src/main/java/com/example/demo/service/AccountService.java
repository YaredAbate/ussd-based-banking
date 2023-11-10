package com.example.demo.service;

import com.example.demo.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long accountId);
    Account saveAccount(Account account);
    void deleteAccount(Long accountId);
}