package com.example.demo.controller;
import com.example.demo.model.MobileBankingUser;
import com.example.demo.service.MobileBankingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/MobileBankingUser")

public class MobileBankingUserController {
    @Autowired
    private MobileBankingUserService mobileBankingUserService;
    @GetMapping
    public List<MobileBankingUser> getAllMobileBankingUser(){
        List<MobileBankingUser> getAllMobileBankingUser=this.mobileBankingUserService.
                getAllMobileBankingUsers();
        return getAllMobileBankingUser;
    }
    @GetMapping("/{id}")
    public Optional<MobileBankingUser> getMobileBankingUserById(@PathVariable long id){
        Optional<MobileBankingUser> getMobileBankingUserById=this.mobileBankingUserService.getMobileBankingUserById(id);
        return getMobileBankingUserById;
    }
    @PostMapping
    public ResponseEntity<MobileBankingUser> createMobileBankingUser(@RequestBody MobileBankingUser mobileBankingUser){
        MobileBankingUser createdMobileBankingUser=this.mobileBankingUserService.createMobileBankingUser(mobileBankingUser);
        return ResponseEntity.ok(createdMobileBankingUser);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MobileBankingUser> updateMobileBankingUser
            (@PathVariable long id, @RequestBody MobileBankingUser mobileBankingUser){
        MobileBankingUser updateMobileBankingUser=this.mobileBankingUserService.
                updateMobileBankingUser(id,mobileBankingUser);
        return ResponseEntity.ok(updateMobileBankingUser);
    }
    @DeleteMapping
    public void deleteMobileBankingUser(@PathVariable long id){
        mobileBankingUserService
                .deleteMobileBankingUser(id);
    }
    @PostMapping("/createForCustomer")
    public ResponseEntity<MobileBankingUser> createMobileBankingUserForCustomer(
            @RequestParam String customerEmail,
            @RequestBody MobileBankingUser mobileBankingUser) {
        MobileBankingUser createdMobileBankingUser = mobileBankingUserService
                .createMobileBankingUserForCustomer(customerEmail, mobileBankingUser);
        return ResponseEntity.ok(createdMobileBankingUser);
    }
}
