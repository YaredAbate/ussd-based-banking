package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        Transaction updateTransaction=this.transactionService.updateTransaction(id,transaction);
        return updateTransaction;
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {

        transactionService.deleteTransaction(id);
    }
    @PostMapping("/transfer")
    public String transferFunds(@RequestParam String fromAccountNumber,
                                @RequestParam String toAccountNumber,
                                @RequestParam double amount) {
        transactionService.transferFunds(fromAccountNumber, toAccountNumber, amount);
        return "Funds transferred successfully";
    }
    @PostMapping("/cash-deposit")
    public String initiateCashDeposit(@RequestParam String accountNumber, @RequestParam double amount) {
        transactionService.initiateCashDeposit(accountNumber, amount);
        return "Cash deposit initiated successfully.";
    }

    @PostMapping("/complete-cash-deposit")
    public String completeCashDeposit(@RequestParam String otp) {
        transactionService.completeCashDeposit(otp);
        return "Cash deposit completed.";
    }

    @PostMapping("/cash-withdrawal")
    public String initiateCashWithdrawal(@RequestParam String accountNumber, @RequestParam double amount) {
        transactionService.initiateCashWithdrawal(accountNumber, amount);
        return "Cash withdrawal initiated successfully.";
    }

    @PostMapping("/complete-cash-withdrawal")
    public String completeCashWithdrawal(@RequestParam String otp) {
        transactionService.completeCashWithdrawal(otp);
        return "Cash withdrawal completed.";
    }
}
