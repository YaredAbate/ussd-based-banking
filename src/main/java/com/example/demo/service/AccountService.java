package com.example.demo.service;

import com.example.demo.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long accountId);
    Account saveAccount(Account account);
    Account updateAccount(long id,Account account);
    void deleteAccount(Long accountId);
}