package com.calvin.lendingapi.repository;

import com.calvin.lendingapi.models.Customer;
import com.calvin.lendingapi.models.Loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByCustomerId(Long custId);
}
