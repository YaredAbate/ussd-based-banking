package com.example.demo.repository;

import com.example.demo.model.MobileBankingUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileBankingUserRepository extends JpaRepository<MobileBankingUser,Long> {
}
