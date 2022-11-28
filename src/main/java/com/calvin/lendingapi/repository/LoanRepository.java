package com.calvin.lendingapi.repository;

import com.calvin.lendingapi.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByCustomerId(Long custId);

   List<Loan> findByExpiryDateLessThanEqual(Date date);
}
