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
    public Loan applyLoan(@RequestBody Loan loan) {
        return loanService.applyLoan(loan);
    }

    @GetMapping("/customer/{id}")
    public List<Loan> getLoansByCustomerId(@PathVariable Long id) {
        return loanService.getLoansByCustomerId(id);
    }

    @GetMapping("/clear/{loanId}")
    public ResponseEntity<String> forecloseLoan(@PathVariable Long loanId) {
        loanService.clearLoan(loanId);
        return new ResponseEntity<String>("Loan Foreclosed with Loan Id: " + loanId, HttpStatus.OK);
    }
}
