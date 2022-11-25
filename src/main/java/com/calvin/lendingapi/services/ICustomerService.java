package com.calvin.lendingapi.services;

import com.calvin.lendingapi.models.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    public List<Customer> getCustomers();

    public Customer addCustomer(Customer c);

    public Customer updateCustomer(Customer c);

    public Optional<Customer> findById(Long id);

    // public Long doLogin(String email, String names);

  

  

    // public Customer getCustomerById(Long id);
}
