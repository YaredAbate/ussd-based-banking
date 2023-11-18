package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/transactions")
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
    @GetMapping("/")
    public List<Transaction> getRecentTransaction(){
        return transactionService.getRecentTransactions();
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
        return "Cash deposit initiated successfully and the OTP is : "+
                transactionService.initiateCashDeposit(accountNumber, amount);
    }

    @PostMapping("/complete-cash-deposit")
    public String completeCashDeposit(@RequestParam String otp,@RequestParam String accountNumber) {
        transactionService.completeCashDeposit(otp,accountNumber);
        return "Cash deposit completed.";
    }

    @PostMapping("/cash-withdrawal")
    public String initiateCashWithdrawal(@RequestParam String accountNumber, @RequestParam double amount) {

        return "Cash withdrawal initiated successfully and the OTP is : "+
                transactionService.initiateCashWithdrawal(accountNumber, amount);
    }

    @PostMapping("/complete-cash-withdrawal")
    public String completeCashWithdrawal(@RequestParam String otp,@RequestParam String accountNumber) {
        transactionService.completeCashWithdrawal(otp,accountNumber);
        return "Cash withdrawal completed.";
    }
}
