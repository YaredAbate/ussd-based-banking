package com.example.demo.service.serviceImpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(long id, Account account) {
        Account updateAccount=this.accountRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Account not exist with id :"+id));
        updateAccount.setAccountNumber(account.getAccountNumber());
        updateAccount.setAccountStatus(account.getAccountStatus());
        updateAccount.setBalance(account.getBalance());
        return this.accountRepository.save(updateAccount);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}

