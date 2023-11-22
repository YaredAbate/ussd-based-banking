package com.example.demo.service.serviceImpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.MobileBankingUser;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.MobileBankingUserRepository;
import com.example.demo.service.MobileBankingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class MobileBankingUserServiceImpl implements MobileBankingUserService {
    @Autowired
    private MobileBankingUserRepository mobileBankingUserRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public MobileBankingUser createMobileBankingUser(MobileBankingUser mobileBankingUser) {
        return this.mobileBankingUserRepository.save(mobileBankingUser);
    }

    @Override
    public MobileBankingUser updateMobileBankingUser(long id, MobileBankingUser mobileBankingUser) {
        MobileBankingUser updateMobileBankingUser=this.mobileBankingUserRepository.
                findById(id).orElseThrow(()->
                        new ResourceNotFoundException("mobileBanking user not found with id : "+id));
        updateMobileBankingUser.setPassword(mobileBankingUser.getPassword());
        updateMobileBankingUser.setPIN(mobileBankingUser.getPIN());
        updateMobileBankingUser.setLanguage(mobileBankingUser.getLanguage());
        updateMobileBankingUser.setIsMobileBankingUser(mobileBankingUser.getIsMobileBankingUser());
        updateMobileBankingUser.setUsername(mobileBankingUser.getUsername());
        return this.mobileBankingUserRepository.save(updateMobileBankingUser);
    }

    @Override
    public Optional<MobileBankingUser> getMobileBankingUserById(Long id) {

        return this.mobileBankingUserRepository.findById(id);
    }

    @Override
    public List<MobileBankingUser> getAllMobileBankingUsers() {
        return this.mobileBankingUserRepository.findAll();
    }

    @Override
    public void deleteMobileBankingUser(Long id) {
     this.mobileBankingUserRepository.deleteById(id);
    }
    @Override
    public MobileBankingUser createMobileBankingUserForCustomer(String email, MobileBankingUser mobileBankingUser) {
        // Find the customer by username
        Customer customer = customerRepository.findByEmail(email);
        if(customer==null){
            throw new ResourceNotFoundException("Customer not found with Email: " + email);
        }else {
            mobileBankingUser.setCustomer(customer);
            return mobileBankingUserRepository.save(mobileBankingUser);
        }
    }
}
