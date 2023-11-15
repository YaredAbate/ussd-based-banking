package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public void deleteCustomer(@PathVariable long id){
        customerService.deleteCustomer(id);
    }
    @GetMapping()
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> getCustomers=this.customerService.getAllCustomers();
        return ResponseEntity.ok(getCustomers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable long id){
        Optional<Customer> getCustomer=this.customerService.getCustomerById(id);
        return ResponseEntity.ok(getCustomer);
    }
}
