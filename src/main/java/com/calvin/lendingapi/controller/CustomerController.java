package com.calvin.lendingapi.controller;

import com.calvin.lendingapi.models.Customer;
import com.calvin.lendingapi.services.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired(required = true)
    ICustomerService customerService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Adding Customer

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
      
        return new ResponseEntity<Customer>(customerService.addCustomer(customer), HttpStatus.OK);
    }

    // Updating Customer

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) {
        return new ResponseEntity<Customer>(customerService.updateCustomer(c), HttpStatus.OK);
    }

    // Fetching all Customers

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<List<Customer>>(customerService.getCustomers(), HttpStatus.OK);
    }

    // Customer Login

    @PostMapping("/login")
    public ResponseEntity<Integer> doLogin(@RequestParam String email, @RequestParam String password) {
        return new ResponseEntity<Integer>(customerService.doLogin(email, password), HttpStatus.OK);
    }

    // Fetching Customer By Customer Id

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        return new ResponseEntity<Customer>(customerService.getCustomerById(id), HttpStatus.OK);
    }
}
