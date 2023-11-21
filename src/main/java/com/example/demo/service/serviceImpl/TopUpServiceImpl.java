package com.example.demo.service.serviceImpl;

import com.example.demo.model.Account;
import com.example.demo.model.TopUp;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.TopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
@Service
public class TopUpServiceImpl implements TopUpService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WebClient webClientForTopUp;
    @Override
    public ResponseEntity<String> topUpfetch(String accountNumber, int amount) {
        // Allowed values
        List<Integer> allowedValues = Arrays.asList(5, 10, 15, 25, 50, 100, 300, 500, 1000);

        // Check if paramValue is in the allowed values
        if (!allowedValues.contains(amount)) {
            // Handle the case where paramValue is not allowed
            return ResponseEntity.badRequest().body("Invalid Value! Allowed values are: " + allowedValues);
        }

        // Retrieve the account using the account number
        Account accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional != null) {

            // Check if the account has enough money to buy
            Double requiredAmount = new Double(amount);
            if (accountOptional.getBalance() - requiredAmount < 0) {
                return ResponseEntity.badRequest().body("Insufficient funds in the account to make the purchase.");
            }

            // Make the API call if paramValue is allowed
            TopUp jsonResponse = webClientForTopUp.get().uri("topup/" + amount).retrieve().bodyToMono(TopUp.class)
                    .block();

            // Check if jsonResponse is not null
            if (jsonResponse != null) {
                // Extract values from the response
                String senum = jsonResponse.getSenum();
                String scnum = jsonResponse.getScnum();
                String expDate = jsonResponse.getExpDate();

                // Deduct the amount from the account
                accountOptional.setBalance(accountOptional.getBalance() - requiredAmount);
                accountRepository.save(accountOptional);


                // Return the extracted values
                return ResponseEntity.ok("senum: " + senum + "\nscnum: " + scnum + "\nexpDate: " + expDate +
                        "\nAmount deducted from account: " + requiredAmount);
            } else {
                // Handle the case where the response is null
                return ResponseEntity.badRequest().body("Unable to retrieve values from the response.");
            }
        } else {
            // Handle the case where the account is not found
            return ResponseEntity.badRequest().body("Account not found.");
        }
    }
}
