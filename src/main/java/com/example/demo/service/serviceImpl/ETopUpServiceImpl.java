package com.example.demo.service.serviceImpl;

import com.example.demo.model.Account;
import com.example.demo.model.ETopUpResponse;
import com.example.demo.model.Transaction;
import com.example.demo.service.AccountService;
import com.example.demo.service.ETopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static com.example.demo.model.enumaration.TransactionStatus.SUCCESS;
import static com.example.demo.model.enumaration.TransactionType.E_TOP_UP;

@Service
public class ETopUpServiceImpl implements ETopUpService {
    @Autowired
    private AccountService accountService;
    @Value("${external.api.url}")
    private String externalApiUrl;
    private final RestTemplate restTemplate;

    public ETopUpServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ETopUpResponse buyCard(int denomination,String accountNumber) {
        String apiUrl = externalApiUrl + "?Denomination=" + denomination;
        ETopUpResponse eTopUpResponse = restTemplate.getForObject(apiUrl, ETopUpResponse.class);
        Account userAccount = accountService.getAccountByAccountNumber(accountNumber);
        if (userAccount != null) {
            double newBalance = userAccount.getBalance() - denomination;
            userAccount.setBalance(newBalance);
            accountService.updateAccount(userAccount);
            Transaction transaction = new Transaction();
            transaction.setTransactionType(E_TOP_UP);
            transaction.setAmount(newBalance);
            transaction.setStatus(SUCCESS);
            transaction.setTransactionDate(LocalDateTime.now());
        } else {
            throw new RuntimeException("User account not found for account number: " + accountNumber);
        }

        return eTopUpResponse;
    }
}
