package com.example.demo.service;

import com.example.demo.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(long id,Customer Customer);
    public Optional<Customer> getCustomerById(Long id);
    public List<Customer> getAllCustomers();
    public void deleteCustomer(Long id);


}
