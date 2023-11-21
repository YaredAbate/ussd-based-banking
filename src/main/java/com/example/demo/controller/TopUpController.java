package com.example.demo.controller;
import com.example.demo.service.TopUpService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/TopUp")
public class TopUpController {

    @Autowired
    private TopUpService topUpService;



    @GetMapping("/topup")
    public ResponseEntity<String> topUp(@RequestParam("accountNumber") String accountNumber,
                                        @RequestParam("paramValue") int paramValue) {
        return topUpService.topUpfetch(accountNumber, paramValue);
    }
}

