package com.caju.transactionvalidator.controller;

import com.caju.transactionvalidator.model.Transaction;
import com.caju.transactionvalidator.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthorizeTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAccount("123456");
        transaction.setTotalAmount(200.00);
        transaction.setMcc("5411");
        transaction.setMerchant("UBER EATS");

        when(transactionService.processTransaction(transaction)).thenReturn("Transaction Authorized");

        ResponseEntity<String> response = transactionController.authorizeTransaction(transaction);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Transaction Authorized", response.getBody());

        verify(transactionService, times(1)).processTransaction(transaction);
    }
}
