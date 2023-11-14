package com.example.demo.controller;
import com.example.demo.model.ETopUpResponse;
import com.example.demo.service.ETopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/etop-up")
public class ETopUpController {

    private final ETopUpService eTopUpService;

    @Autowired
    public ETopUpController(ETopUpService eTopUpService) {
        this.eTopUpService = eTopUpService;
    }

    @GetMapping("/buy-card")
    public ETopUpResponse buyCard(@RequestParam int denomination, @RequestParam String accountNumber) {
        return eTopUpService.buyCard(denomination,accountNumber);
    }
}

