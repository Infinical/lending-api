package com.calvin.lendingapi.exceptions;

public class TransactionsNotFoundException extends RuntimeException{

    public TransactionsNotFoundException(String message) {
        super(message);
    }
}
