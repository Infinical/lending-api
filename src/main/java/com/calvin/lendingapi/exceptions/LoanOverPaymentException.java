package com.calvin.lendingapi.exceptions;

public class LoanOverPaymentException extends RuntimeException{
    public LoanOverPaymentException(String message){
        super(message);
    }
}
