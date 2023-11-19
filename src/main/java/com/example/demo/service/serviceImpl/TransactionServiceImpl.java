package com.example.demo.service.serviceImpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.model.History;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.model.enumaration.CustomerType.MERCHANT;
import static com.example.demo.model.enumaration.TransactionStatus.*;
import static com.example.demo.model.enumaration.TransactionType.CASH_DEPOSIT;
import static com.example.demo.model.enumaration.TransactionType.CASH_WITHDRAWAL;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction updateTransaction = this.transactionRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id : " + id));
        updateTransaction.setAccountNum(transaction.getAccountNum());
        updateTransaction.setAmount(transaction.getAmount());
        return updateTransaction;
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public String transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accountService.getAccountByAccountNumber(fromAccountNumber);
        Account toAccount = accountService.getAccountByAccountNumber(toAccountNumber);

        if (fromAccount != null && toAccount != null && fromAccount.getBalance()>=amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            // Update account balances in the database
            accountService.updateAccount(fromAccount);
            accountService.updateAccount(toAccount);

            // Record the fund transfer
            Transaction fundTransfer = new Transaction();
            fundTransfer.setAccountNum(fromAccountNumber);
            fundTransfer.setAccountNum(toAccountNumber);
            fundTransfer.setAmount(amount);
            fundTransfer.setTransactionDate(LocalDateTime.now());
            transactionRepository.save(fundTransfer);
            History history=new History();
            history.setTransactionDate(LocalDateTime.now());
            history.setAccountNumber(fromAccountNumber);
            history.setAccountNumber(toAccountNumber);
            history.setAmount(amount);
            historyRepository.save(history);
            return "Funds transferred successfully";
        }
        else{
            return "Fund transfer UnSuccesfull";
        }
    }

    @Override
    public String initiateCashDeposit(String accountNumber, double amount) {
        String otp = generateOTP();
        Transaction transaction = new Transaction();
        transaction.setTransactionType(CASH_DEPOSIT);
        transaction.setAccountNum(accountNumber);
        transaction.setAmount(amount);
        transaction.setOtp(otp);
        transaction.setStatus(PENDING);
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);
        History history=new History();
        history.setTransactionDate(LocalDateTime.now());
        history.setAccountNumber(accountNumber);
        history.setAmount(amount);
        historyRepository.save(history);
        return otp;
    }

    @Override
    public String completeCashDeposit(String otp,String accountNumber) {
        Transaction transaction = transactionRepository.findByOtpAndStatus(otp, PENDING);
        Account accountBalance=accountService.getAccountByAccountNumber(accountNumber);
        if (transaction != null) {
            if (isOTPValid(transaction.getTransactionDate())) {
                transaction.setStatus(SUCCESS);
                transaction.setTransactionDate(LocalDateTime.now());
                transactionRepository.save(transaction);
                History history=new History();
                history.setTransactionDate(LocalDateTime.now());
                history.setTransactionCode(transaction.getTransactionCode());
                historyRepository.save(history);
                // Update customer account
                Account customerAccount = accountService.getAccountByAccountNumber(transaction.
                        getAccountNum());
                Account merchantAccount=accountRepository.findByAccountNumberAndCustomerType(accountNumber,MERCHANT);
                if (customerAccount != null && merchantAccount !=null &&
                        merchantAccount.getBalance()>=transaction.getAmount()) {
                    double newBalance = customerAccount.getBalance() + transaction.getAmount();
                    customerAccount.setBalance(newBalance);
                    accountService.updateAccount(customerAccount);
                    double newMerchantBalance=merchantAccount.getBalance()-transaction.getAmount();
                     merchantAccount.setBalance(newMerchantBalance);
                    accountService.updateAccount(merchantAccount);

                    return("Cash deposit completed successfully. Customer account and Merchant account updated.");
                } else {
                    return("Cash deposit failed. " +
                            "Customer account or merchant account not found or merchant account doesn't have enough money.");
                }
            } else {
                transaction.setStatus(FAIL);
                transaction.setTransactionDate(LocalDateTime.now());
                transactionRepository.save(transaction);

                return("Cash deposit failed. OTP expired.");
            }
        } else {
            return ("Cash deposit failed. Invalid OTP or transaction not found.");
        }
    }

    @Override
    public String initiateCashWithdrawal(String accountNumber, double amount) {
        String otp = generateOTP();
        Transaction transaction = new Transaction();
        transaction.setTransactionType(CASH_WITHDRAWAL);
        transaction.setAccountNum(accountNumber);
        transaction.setAmount(amount);
        transaction.setOtp(otp);
        transaction.setStatus(PENDING);
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);
        History history=new History();
        history.setTransactionDate(LocalDateTime.now());
        history.setAccountNumber(accountNumber);
        history.setAmount(amount);
        historyRepository.save(history);
        return otp;
    }
    @Override
    public String merchantCashWithdrawal(String accountNumber, double amount) {
        Account merchantAccount = accountRepository.findByAccountNumberAndCustomerType(accountNumber, MERCHANT);
        if (merchantAccount != null) {
            if (merchantAccount.getBalance() >= amount) {
                Transaction transaction = new Transaction();
                transaction.setTransactionType(CASH_WITHDRAWAL);
                transaction.setAccountNum(accountNumber);
                transaction.setAmount(amount);
                transaction.setStatus(SUCCESS);
                transaction.setTransactionDate(LocalDateTime.now());
                transactionRepository.save(transaction);
                History history = new History();
                history.setTransactionDate(LocalDateTime.now());
                history.setAccountNumber(accountNumber);
                history.setAmount(amount);
                historyRepository.save(history);
                double newBalance = merchantAccount.getBalance() - amount;
                merchantAccount.setBalance(newBalance);
                accountService.updateAccount(merchantAccount);
                return "Cash Withdrawal Successful";
            } else {
                return "Cash withdrawal is not sucessful";
            }
        }
        else{
            return "the Customer Type is not merchant";
        }
    }

    @Override
    public String merchantCashDeposit(String accountNumber, double amount) {
        Account merchantAccount = accountRepository.findByAccountNumberAndCustomerType(accountNumber, MERCHANT);
        if (merchantAccount != null) {
                Transaction transaction = new Transaction();
                transaction.setTransactionType(CASH_DEPOSIT);
                transaction.setAccountNum(accountNumber);
                transaction.setAmount(amount);
                transaction.setStatus(SUCCESS);
                transaction.setTransactionDate(LocalDateTime.now());
                transactionRepository.save(transaction);
                History history = new History();
                history.setTransactionDate(LocalDateTime.now());
                history.setAccountNumber(accountNumber);
                history.setAmount(amount);
                historyRepository.save(history);
                double newBalance = merchantAccount.getBalance() + amount;
                merchantAccount.setBalance(newBalance);
                accountService.updateAccount(merchantAccount);
                return "Cash Deposit Successful";
            } else {
                return "Cash Deposit is not sucessful because the Customer Type is not merchant";
            }
        }

    @Override
    public void completeCashWithdrawal(String otp,String accountNumber) {
        Transaction transaction = transactionRepository.findByOtpAndStatus(otp, PENDING);
        if (transaction != null) {
            if (isOTPValid(transaction.getTransactionDate())) {
                transaction.setStatus(SUCCESS);
                transaction.setTransactionDate(LocalDateTime.now());
                transactionRepository.save(transaction);
                History history=new History();
                history.setTransactionDate(LocalDateTime.now());
                history.setTransactionCode(transaction.getTransactionCode());
                historyRepository.save(history);
                Account customerAccount = accountService.getAccountByAccountNumber
                        (transaction.getAccountNum());
                Account merchantAccount=accountRepository.findByAccountNumberAndCustomerType
                        (accountNumber,MERCHANT);
                if (customerAccount != null && merchantAccount  !=null) {
                    double newBalance = customerAccount.getBalance() - transaction.getAmount();
                    customerAccount.setBalance(newBalance);
                    accountService.updateAccount(customerAccount);
                    double newMerchantBalance=merchantAccount.getBalance()+transaction.getAmount();
                    merchantAccount.setBalance(newMerchantBalance);
                    accountService.updateAccount(merchantAccount);
                    System.out.println("Cash withdrawal completed successfully. Customer account updated.");
                } else {
                    System.out.println("Cash withdrawal failed. Customer account not found.");
                }

            } else {
                transaction.setStatus(FAIL);
                transaction.setTransactionDate(LocalDateTime.now());
                transactionRepository.save(transaction);
                System.out.println("Cash withdrawal failed. OTP expired.");
            }
        } else {
            System.out.println("Cash withdrawal failed. Invalid OTP or transaction not found.");
        }
    }

    @Override
    public List<Transaction> getRecentTransactions() {
        return transactionRepository.findTop5ByOrderByTransactionDateDesc();
    }

    private String generateOTP() {
        int otpLength = 6;

        String allowedChars = "0123456789";

        StringBuilder otp = new StringBuilder(otpLength);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < otpLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            otp.append(allowedChars.charAt(randomIndex));
        }

        return otp.toString();
    }
    private boolean isOTPValid(LocalDateTime creationDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        long minutesElapsed = Duration.between(creationDateTime, currentDateTime).toMinutes();
        return minutesElapsed <= 30;
    }

}
