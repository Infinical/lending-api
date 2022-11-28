package com.calvin.lendingapi.services;

import com.calvin.lendingapi.exceptions.CustomerNotFoundException;
import com.calvin.lendingapi.models.Customer;
import com.calvin.lendingapi.repository.CustomerRepository;
import com.calvin.lendingapi.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerSeviceImplTest {

    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository repository;

    @Test
    void should_add_customer(){
        // Arrange
        final var customerToSave = Customer.builder().names("Calvin Mokua").email("test@gmail.com").idNumber("0000000").phoneNumber("25479065432").taxPin("19101").build();
        when(repository.save(any(Customer.class))).thenReturn(customerToSave);

        // Act
        final var actual = service.addCustomer(new Customer());

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(customerToSave);
        verify(repository, times(1)).save(any(Customer.class));
//        verifyNoMoreInteractions(repository);
    }


    @Test
    void should_find_and_return_one_customer() {
        // Arrange
        final var expectedCustomer = Customer.builder().names("Jimmy Olsen").email("jimmy@gmail.com").build();
        when(repository.findById((long) anyInt())).thenReturn(Optional.of(expectedCustomer));

        // Act
        final var actual = service.findById(Long.valueOf(getRandomInt()) );

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedCustomer);
        verify(repository, times(1)).findById(Long.valueOf(anyInt()));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_not_found_a_customer_that_doesnt_exists() {
        // Arrange
       lenient().when(repository.findById(Long.valueOf(anyInt()))).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(CustomerNotFoundException.class, () -> service.findById(Long.valueOf(getRandomInt())));
        verify(repository, times(1)).findById(Long.valueOf(anyInt()));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_find_and_return_all_customers() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of(new Customer(), new Customer()));

        // Act & Assert
        assertThat(service.getCustomers()).hasSize(2);
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }



    private int getRandomInt() {
        return new Random().ints(1, 10).findFirst().getAsInt();
    }
}
