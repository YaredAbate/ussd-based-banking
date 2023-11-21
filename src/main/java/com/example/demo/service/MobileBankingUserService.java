package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.MobileBankingUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MobileBankingUserService {
    public MobileBankingUser createMobileBankingUser(MobileBankingUser mobileBankingUser);
    public MobileBankingUser updateMobileBankingUser(long id,MobileBankingUser mobileBankingUser);
    public Optional<MobileBankingUser> getMobileBankingUserById(Long id);
    public List<MobileBankingUser> getAllMobileBankingUsers();
    public void deleteMobileBankingUser(Long id);
    public MobileBankingUser createMobileBankingUserForCustomer
            (String customerUsername, MobileBankingUser mobileBankingUser);

}
