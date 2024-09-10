package com.caju.transactionvalidator.modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.caju.transactionvalidator.model.Balance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BalanceTest {

    private Balance balance;

    @BeforeEach
    public void setUp() {
        balance = new Balance(1000.00, 500.00, 200.00); // Inicialize com saldos específicos
    }

    @Test
    public void testGetBalance() {
        assertEquals(200.00, balance.getBalance("CASH"));
        assertEquals(500.00, balance.getBalance("MEAL"));
        assertEquals(1000.00, balance.getBalance("FOOD"));
    }

    @Test
    public void testReduceBalance() {
        balance.reduceBalance("CASH", 100.00);
        assertEquals(100.00, balance.getBalance("CASH"));

        balance.reduceBalance("MEAL", 50.00);
        assertEquals(450.00, balance.getBalance("MEAL"));

        balance.reduceBalance("FOOD", 25.00);
        assertEquals(975.00, balance.getBalance("FOOD"));
    }

    @Test
    public void testReduceBalance_InsufficientFunds() {
        assertThrows(IllegalArgumentException.class, () -> {
            balance.reduceBalance("CASH", 1200.00);
        });
    }

    @Test
    public void testGetBalance_InvalidCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            balance.getBalance("INVALID_CATEGORY");
        });
    }
}
