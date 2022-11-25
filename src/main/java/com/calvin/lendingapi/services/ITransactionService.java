package com.calvin.lendingapi.services;

import com.calvin.lendingapi.models.Transaction;

import java.util.List;

public interface ITransactionService {
    public Transaction addTransaction(Transaction trans);

    public List<Transaction> getTransactionsByCustId(int custId);
}
