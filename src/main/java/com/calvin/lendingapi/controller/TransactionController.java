package com.calvin.lendingapi.controller;

import com.calvin.lendingapi.models.Transaction;
import com.calvin.lendingapi.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired(required = true)
    private ITransactionService transactionService;

    @PostMapping("/")
    public Transaction addTransaction(@RequestBody Transaction trans) {
        return transactionService.addTransaction(trans);
    }

    @GetMapping("/customer/{id}")
    public List<Transaction> getTransactionsByCustId(@PathVariable Long id) {
        return transactionService.getTransactionsByCustId(id);
    }
}
