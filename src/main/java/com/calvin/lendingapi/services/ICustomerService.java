package com.calvin.lendingapi.services;

import com.calvin.lendingapi.models.Customer;

import java.util.List;

public interface ICustomerService {
    public Integer doLogin(String email, String password);

    public Customer addCustomer(Customer c);

    public Customer updateCustomer(Customer c);

    public List<Customer> getCustomers();

    public Customer getCustomerById(int custId);
}
