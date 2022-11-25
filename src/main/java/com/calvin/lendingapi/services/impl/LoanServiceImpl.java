package com.calvin.lendingapi.services.impl;

import com.calvin.lendingapi.exceptions.CustomerNotFoundException;
import com.calvin.lendingapi.exceptions.LoanNotFoundException;
import com.calvin.lendingapi.models.Customer;
import com.calvin.lendingapi.models.Loan;
import com.calvin.lendingapi.repository.CustomerRepository;
import com.calvin.lendingapi.repository.LoanRepository;
import com.calvin.lendingapi.services.ILoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class LoanServiceImpl implements ILoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Loan applyLoan(Loan loan)  {
       Optional <Customer> customer = customerRepository.findById(loan.getCustomer().getId());
        if (customer.isEmpty()) {
           throw new CustomerNotFoundException("Customer not found");
        }
        var cust = customer.get();
        cust.addLoan(loan);
        // loan.setCustomer(cust);
        // customer.get().addLoan(loan);
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoansByCustomerId(Long customerId) {
       
        Optional <Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
           throw new CustomerNotFoundException("Customer not found");
        }

       return loanRepository.findByCustomerId(customerId);
        // System.out.println(customer.getEmail());
                // .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
    }

    @Override
    public void foreCloseLoan(int loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
        loanRepository.delete(loan);
    }
}
