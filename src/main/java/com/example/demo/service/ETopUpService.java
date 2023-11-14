package com.example.demo.service;

import com.example.demo.model.ETopUpResponse;

public interface ETopUpService {
    ETopUpResponse buyCard(int denomination,String accountNumber);
}
