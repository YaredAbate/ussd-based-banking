package com.example.demo.service;

import com.example.demo.model.TopUp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TopUpService {
  // TopUp buyCard(int denomination, String accountNumber);


    ResponseEntity<String> topUpfetch(String accountNumber, int amount);
}
