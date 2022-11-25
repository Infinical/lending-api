package com.calvin.lendingapi.services;

import com.calvin.lendingapi.models.Loan;

import java.util.List;

public interface ILoanService {
    public Loan applyLoan(Loan l);

    public List<Loan> getLoansByCustomerId(int custId);

    public void foreCloseLoan(int loanId);
}
