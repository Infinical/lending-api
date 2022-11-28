package com.calvin.lendingapi.services;

import com.calvin.lendingapi.models.Loan;
import com.calvin.lendingapi.models.Transaction;
import com.calvin.lendingapi.repository.CustomerRepository;
import com.calvin.lendingapi.repository.LoanRepository;
import com.calvin.lendingapi.repository.TransactionRepository;
import com.calvin.lendingapi.services.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
    @InjectMocks
    private TransactionServiceImpl service;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private TransactionRepository transactionRepository;


    @Test
    void should_add_new_transaction(){
        // Arrange
        final var transactionToSave = Transaction.builder().amount(1000).loan(Loan.builder().id(Long.valueOf(1)).build()).build();
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToSave);

        // Act
        final var actual = service.addTransaction(new Transaction());

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(transactionToSave);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
//        verifyNoMoreInteractions(repository);
    }

//    @Test
//    void should_get_transactions_by_customer(){
//        // Arrange
//        final var customerToSave = Customer.builder().names("Calvin Mokua").email("test@gmail.com").idNumber("0000000").phoneNumber("25479065432").taxPin("19101").build();
//        when(repository.save(any(Customer.class))).thenReturn(customerToSave);
//
//        // Act
//        final var actual = service.addCustomer(new Customer());
//
//        // Assert
//        assertThat(actual).usingRecursiveComparison().isEqualTo(customerToSave);
//        verify(repository, times(1)).save(any(Customer.class));
////        verifyNoMoreInteractions(repository);
//    }
}
