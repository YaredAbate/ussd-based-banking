package com.example.demo.service.serviceImpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.MobileBankingUser;
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
        return this.mobileBankingUserRepository.save(updateMobileBankingUser);
    }

    @Override
    public Optional<MobileBankingUser> getMobileBankingUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<MobileBankingUser> getAllMobileBankingUsers() {
        return null;
    }

    @Override
    public void deleteMobileBankingUser(Long id) {
     this.mobileBankingUserRepository.deleteById(id);
    }
}
