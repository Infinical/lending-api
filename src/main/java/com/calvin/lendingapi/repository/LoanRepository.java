package com.calvin.lendingapi.repository;

import com.calvin.lendingapi.models.Customer;
import com.calvin.lendingapi.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    Customer findByCustomerId(int custId);
}
