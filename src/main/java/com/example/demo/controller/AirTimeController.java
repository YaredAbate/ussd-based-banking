package com.example.demo.controller;
import com.example.demo.model.AirTime;
import com.example.demo.service.AirTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/airTime")
public class AirTimeController {

    @Autowired
    private AirTimeService airTimeService;

    @GetMapping("/buy-card")
    public AirTime buyCard(@RequestParam int denomination, @RequestParam String accountNumber) {
        return airTimeService.buyCard(denomination,accountNumber);
    }
}

