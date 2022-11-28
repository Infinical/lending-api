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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        var getCustomer = customer.get();
        getCustomer.addLoan(loan);
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoansByCustomerId(Long customerId) {
       
        Optional <Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
           throw new CustomerNotFoundException("Customer not found");
        }

       return loanRepository.findByCustomerId(customerId);
    }

    @Override
    public void clearLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
        loan.setForeClosed(true);
        loanRepository.save(loan);
    }

    @Override
    @Scheduled(cron = "@daily")
    @Async
    public void markDefaulted()  {
        logger.info("Marking Default");
        List<Loan> loans = loanRepository.findByExpiryDateLessThanEqual(new Date());
        loans.forEach(loan -> {
            Optional<Loan> fetchedLoan = loanRepository.findById(loan.getId());
            fetchedLoan.get().setDefaulted(true);
            System.out.println(fetchedLoan.get().isDefaulted());
            loanRepository.save(fetchedLoan.get());
        });
//

//        loanRepository.saveAll(loans);
    }
}
