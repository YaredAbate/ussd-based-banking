package com.example.demo.repository;

import com.example.demo.model.Account;
import com.example.demo.model.enumaration.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumber(String accountNumber);
    Account findByAccountNumberAndCustomerType(String accountNumber, CustomerType customerType);
}
