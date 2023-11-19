package com.example.demo.service;

import com.example.demo.model.AirTime;
import org.springframework.stereotype.Service;

@Service
public interface AirTimeService {
    AirTime buyCard(int denomination, String accountNumber);
}
