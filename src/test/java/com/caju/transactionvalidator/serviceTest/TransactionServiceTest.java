package com.caju.transactionvalidator.serviceTest;

import com.caju.transactionvalidator.model.Transaction;
import com.caju.transactionvalidator.model.Balance;
import com.caju.transactionvalidator.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionService transactionService;
    private Balance balance;

    @BeforeEach
    public void setUp() {
        balance = mock(Balance.class);
        transactionService = new TransactionService(balance);
    }

    @Test
    public void testProcessTransaction_WithSufficientCategoryBalance() {
        // Configuração
        Transaction transaction = new Transaction();
        transaction.setAccount("123");
        transaction.setTotalAmount(100.00);
        transaction.setMcc("5811");
        transaction.setMerchant("UBER EATS");

        when(balance.getBalance("MEAL")).thenReturn(200.00);
        when(balance.getBalance("CASH")).thenReturn(1000.00);

        // Ação
        String response = transactionService.processTransaction(transaction);

        // Verificação
        assertEquals("{ \"code\": \"00\" }", response);
        verify(balance).reduceBalance("MEAL", 100.00);
    }

    @Test
    public void testProcessTransaction_WithFallbackToCash() {
        // Configuração
        Transaction transaction = new Transaction();
        transaction.setAccount("123");
        transaction.setTotalAmount(500.00);
        transaction.setMcc("5811");
        transaction.setMerchant("UNKNOWN MERCHANT");

        when(balance.getBalance("MEAL")).thenReturn(100.00);
        when(balance.getBalance("CASH")).thenReturn(600.00);

        // Ação
        String response = transactionService.processTransaction(transaction);

        // Verificação
        assertEquals("{ \"code\": \"00\" }", response);
        verify(balance).reduceBalance("CASH", 500.00);
    }

    @Test
    public void testProcessTransaction_WithInsufficientCashBalance() {
        // Configuração
        Transaction transaction = new Transaction();
        transaction.setAccount("123");
        transaction.setTotalAmount(1200.00);
        transaction.setMcc("5811");
        transaction.setMerchant("UNKNOWN MERCHANT");

        when(balance.getBalance("MEAL")).thenReturn(100.00);
        when(balance.getBalance("CASH")).thenReturn(1000.00);

        // Ação
        String response = transactionService.processTransaction(transaction);

        // Verificação
        assertEquals("{ \"code\": \"51\" }", response);
    }

    @Test
    public void testProcessTransaction_WithException() {
        // Configuração
        Transaction transaction = new Transaction();
        transaction.setAccount("123");
        transaction.setTotalAmount(100.00);
        transaction.setMcc("5811");
        transaction.setMerchant("UNKNOWN MERCHANT");

        when(balance.getBalance(anyString())).thenThrow(new RuntimeException("Database error"));

        // Ação
        String response = transactionService.processTransaction(transaction);

        // Verificação
        assertEquals("{ \"code\": \"07\" }", response);
    }
}