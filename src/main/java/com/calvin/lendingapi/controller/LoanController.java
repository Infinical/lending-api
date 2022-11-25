package com.calvin.lendingapi.controller;


import com.calvin.lendingapi.services.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.calvin.lendingapi.models.Loan;

import java.util.List;

@RestController
@RequestMapping("/loan")
@CrossOrigin(origins = "*")
public class LoanController {
    @Autowired
    private ILoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan) {
        return new ResponseEntity<Loan>(loanService.applyLoan(loan), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Loan>> getLoansByCustomerId(@PathVariable int id) {
        return new ResponseEntity<List<Loan>>(loanService.getLoansByCustomerId(id), HttpStatus.OK);
    }

    @DeleteMapping("/foreclose/{loanId}")
    public ResponseEntity<String> forecloseLoan(@PathVariable int loanId) {
        loanService.foreCloseLoan(loanId);
        return new ResponseEntity<String>("Loan Foreclosed with Loan Id: " + loanId, HttpStatus.OK);
    }
}
