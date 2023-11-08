package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer createdCustomer=this.customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id,@RequestBody Customer customer){
        Customer updateCustomer=this.customerService.updateCustomer(id,customer);
        return ResponseEntity.ok(updateCustomer);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    //@GetMapping()
    //public ResponseEntity<List<Customer>> getCustomer()
}
