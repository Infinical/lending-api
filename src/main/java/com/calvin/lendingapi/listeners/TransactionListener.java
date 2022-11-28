package com.calvin.lendingapi.listeners;

import com.calvin.lendingapi.exceptions.LoanNotFoundException;
import com.calvin.lendingapi.models.Loan;
import com.calvin.lendingapi.models.Transaction;
import com.calvin.lendingapi.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import java.util.Optional;

@Component
public class TransactionListener {


  private static LoanRepository loanRepo;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository){
        loanRepo = loanRepository;
    }


    @PrePersist
    public void  setLoanBalance(Transaction transaction){


//        transaction.setCreatedAt(new Date());
        Optional<Loan> loan = this.loanRepo.findById(transaction.getLoan().getId());
//
        if(loan.isEmpty()){
            throw new LoanNotFoundException("Loan not found");
        }
//
        double loanBalance = loan.get().getLoanBalance() - transaction.getAmount();

        if (loan.get().getLoanBalance() < transaction.getAmount()){
            double overPayment = loan.get().getLoanBalance() - transaction.getAmount();
            transaction.setAmount((transaction.getAmount() + overPayment));
            loan.get().setLoanBalance((overPayment + (overPayment * (-1))));
//            throw new LoanOverPaymentException("You have an overpayment of " + overPayment);
        }

        if (loan.get().getLoanBalance() > transaction.getAmount() || loan.get().getLoanBalance() == transaction.getAmount()){
            loan.get().setLoanBalance(loanBalance);
        }

    }

    @PostPersist
    public  void viewTransactions(Transaction transaction){
        System.out.println("Transactipon balance");
    }
}
