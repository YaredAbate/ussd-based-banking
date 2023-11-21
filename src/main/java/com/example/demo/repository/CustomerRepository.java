package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.enumaration.IsMobileBankingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByMobileBankingUser_Username(String customerUsername);
    Customer findByEmail(String email);
    Customer findByAccounts_AccountNumberAndMobileBankingUser_IsMobileBankingUser
            (String accountNumber, IsMobileBankingUser isMobileBankingUser);
}
