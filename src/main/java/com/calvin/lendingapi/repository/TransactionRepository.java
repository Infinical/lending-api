package com.calvin.lendingapi.repository;

import com.calvin.lendingapi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("select t from Transaction t inner join Loan l on l.id=t.loan.id where l.customer.id=?1")
    List<Transaction> findTransactionsByCustomerId(int customerId);
}
