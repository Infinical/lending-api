package com.calvin.lendingapi.exceptions;

public class CustomerAlreadyRegisteredException extends RuntimeException{
    public CustomerAlreadyRegisteredException(String message) {
        super(message);
    }
}
