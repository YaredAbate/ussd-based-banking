package com.example.demo.service.serviceImpl;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(long id,Customer customer) {
        Customer updateCustomer=this.customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not exist with id: " + id));
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setMobileNo(customer.getMobileNo());
        updateCustomer.setCity(customer.getCity());
        updateCustomer.setDob(customer.getDob());
        updateCustomer.setHomePhone(customer.getHomePhone());
        updateCustomer.setCountry(customer.getCountry());
        updateCustomer.setMiddleName(customer.getMiddleName());
        updateCustomer.setMobileBankingUser(customer.getMobileBankingUser());
        updateCustomer.setAccounts(customer.getAccounts());
        return  this.customerRepository.save(updateCustomer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);
    }

}
