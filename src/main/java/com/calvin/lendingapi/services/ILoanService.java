package com.calvin.lendingapi.services;

import com.calvin.lendingapi.models.Loan;

import java.util.List;

public interface ILoanService {
    public Loan applyLoan(Loan l);

    public List<Loan> getLoansByCustomerId(Long custId);

    public void clearLoan(Long loanId);

    public void markDefaulted();


}
