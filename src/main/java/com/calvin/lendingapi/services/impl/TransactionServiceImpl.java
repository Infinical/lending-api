package com.calvin.lendingapi.services.impl;

import com.calvin.lendingapi.exceptions.CustomerNotFoundException;
import com.calvin.lendingapi.exceptions.LoanNotFoundException;
import com.calvin.lendingapi.exceptions.TransactionsNotFoundException;
import com.calvin.lendingapi.models.Customer;
import com.calvin.lendingapi.models.Loan;
import com.calvin.lendingapi.models.Transaction;
import com.calvin.lendingapi.repository.CustomerRepository;
import com.calvin.lendingapi.repository.LoanRepository;
import com.calvin.lendingapi.repository.TransactionRepository;
import com.calvin.lendingapi.services.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Transaction addTransaction(Transaction transaction) {

        Optional<Loan> loan = loanRepository.findById(transaction.getLoan().getId());

        if (loan.isEmpty()) {
            throw new LoanNotFoundException("Loan not found");
        }
        var getLoan = loan.get();
        getLoan.addTransaction(transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByCustId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        try {
            List<Transaction> transactions = transactionRepository.findTransactionsByCustomerId(customerId);
            return transactions;
        } catch (Exception e) {
            throw new TransactionsNotFoundException("Transactions not Found for Customer Id: " + customerId);
        }
    }
}
