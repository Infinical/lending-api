package com.calvin.lendingapi.services;

import com.calvin.lendingapi.models.Customer;
import com.calvin.lendingapi.models.Loan;
import com.calvin.lendingapi.repository.CustomerRepository;
import com.calvin.lendingapi.repository.LoanRepository;
import com.calvin.lendingapi.services.impl.LoanServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanServiceImplTest {
    @InjectMocks
    private LoanServiceImpl service;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private LoanRepository loanRepository;


    @Test
    void should_apply_loan(){
        // Arrange
        final var loanToSave = Loan.builder().loanAmount(1000).duration(1).customer(Customer.builder().id(Long.valueOf(1)).build()).build();
        when(loanRepository.save(any(Loan.class))).thenReturn(loanToSave);

        // Act
        final var actual = service.applyLoan(new Loan());

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(loanToSave);
        verify(loanRepository, times(1)).save(any(Loan.class));
//        verifyNoMoreInteractions(repository);
    }


//    @Test
//    void should_get_lonas_by_customer(){
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
//
//    @Test
//    void should_clear_loan(){
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
