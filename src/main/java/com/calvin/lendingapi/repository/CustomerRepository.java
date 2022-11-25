package com.calvin.lendingapi.repository;

import com.calvin.lendingapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByEmailAndNames(String email, String names);
}
