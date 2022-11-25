package com.calvin.lendingapi.repository;

import com.calvin.lendingapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAll();

    Customer findById(Long id);

    Integer findCustomerByEmailAndNames(String email, String names);
}
