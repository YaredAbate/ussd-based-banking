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
    public List<Transaction> getRecentTransaction(@RequestParam String accountNumber){
        return transactionService.getRecentTransactions(accountNumber);
    }
    /*@PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }*/

   /* @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        Transaction updateTransaction=this.transactionService.updateTransaction(id,transaction);
        return updateTransaction;
    }*/

   /* @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {

        transactionService.deleteTransaction(id);
    }*/
    @PostMapping("/transfer")
    public String transferFunds(@RequestParam String fromAccountNumber,
                                @RequestParam String toAccountNumber,
                                @RequestParam double amount) {

        return  transactionService.transferFunds(fromAccountNumber, toAccountNumber, amount);
    }
    @PostMapping("/cash-deposit")
    public String initiateCashDeposit(@RequestParam String accountNumber, @RequestParam double amount) {
        return "Cash deposit initiated successfully and the OTP is : "+
                transactionService.initiateCashDeposit(accountNumber, amount);
    }

    @PostMapping("/complete-cash-deposit")
    public String completeCashDeposit(@RequestParam String otp,@RequestParam String accountNumber) {
        return transactionService.completeCashDeposit(otp,accountNumber);
    }
    @PostMapping("/merchant-cash-deposit")
    public String merchantCashDeposit(@RequestParam String accountNumber, @RequestParam double amount){
        return transactionService.merchantCashDeposit(accountNumber,amount);
    }
    @PostMapping("/merchant-cash-withdrawal")
    public String merchantCashWithdrawal(@RequestParam String accountNumber, @RequestParam double amount){
        return transactionService.merchantCashWithdrawal(accountNumber,amount);
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
