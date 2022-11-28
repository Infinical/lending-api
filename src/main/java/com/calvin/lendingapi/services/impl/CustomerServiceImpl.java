package com.calvin.lendingapi.services.impl;

import com.calvin.lendingapi.exceptions.CustomerAlreadyRegisteredException;
import com.calvin.lendingapi.exceptions.CustomerNotFoundException;
import com.calvin.lendingapi.models.Customer;
import com.calvin.lendingapi.repository.CustomerRepository;
import com.calvin.lendingapi.services.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Customer addCustomer(Customer c) {
        // System.out.println(c);
       Customer customer = customerRepository.findCustomerByEmailAndNames(c.getEmail(), c.getNames());
      
        if (customer != null) {
            System.out.println(customer.getEmail());
            throw new CustomerAlreadyRegisteredException("Customer Already Registered: " );
        }
        // return null;
       return customerRepository.save(c);
    }


    public Customer updateCustomer(Customer c) {
        Customer customer = customerRepository.findById(c.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + c.getId()));
        BeanUtils.copyProperties(c, customer);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found")));
    }
}
