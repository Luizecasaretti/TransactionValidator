package com.caju.transactionvalidator.controller;

import com.caju.transactionvalidator.model.Transaction;
import com.caju.transactionvalidator.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/authorize")
    public ResponseEntity<String> authorizeTransaction(@RequestBody Transaction transaction) {
        String response = transactionService.processTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
