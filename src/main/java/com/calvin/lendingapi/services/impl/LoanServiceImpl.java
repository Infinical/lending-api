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

@Service
@Primary
public class LoanServiceImpl implements ILoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Loan applyLoan(Loan loan) {
        int customerId = Math.toIntExact(loan.getCustomer().getId());
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Cusotmer Not Found: " + customerId));
        customer.addLoan(loan);
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoansByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        return customer.getLoans();
    }

    @Override
    public void foreCloseLoan(int loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
        loanRepository.delete(loan);
    }
}
