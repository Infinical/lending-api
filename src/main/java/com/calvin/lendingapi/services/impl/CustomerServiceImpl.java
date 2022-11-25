package com.calvin.lendingapi.services.impl;

import com.calvin.lendingapi.exceptions.CustomerAlreadyRegisteredException;
import com.calvin.lendingapi.exceptions.CustomerNotFoundException;
import com.calvin.lendingapi.models.Customer;
import com.calvin.lendingapi.repository.CustomerRepository;
import org.slf4j.Logger;
import com.calvin.lendingapi.services.ICustomerService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Customer addCustomer(Customer c) {
        // System.out.println(c);
        Customer customer = customerRepository.findById(c.getId());
        if (customer != null) {
            throw new CustomerAlreadyRegisteredException("Customer Already Registered: " + customer.getId());
        }
        // return null;
       return customerRepository.save(c);
    }

    @Override
    public Integer doLogin(String email, String names) {
        Integer customerId = null;
        try {
            customerId = customerRepository.findCustomerByEmailAndNames(email, names);
            logger.info( customerId + " Logged In Successfully");
            return customerId;
        } catch (Exception e) {
            throw new CustomerNotFoundException("Not Found: " + customerId);
        }
    }

    public Customer updateCustomer(Customer c) {
        Customer customer = customerRepository.findById(Math.toIntExact(c.getId()))
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + c.getId()));
        BeanUtils.copyProperties(c, customer);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        logger.info("Customer Found: " + customerId);
        return customer;
    }
}
