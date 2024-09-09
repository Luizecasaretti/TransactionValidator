package com.caju.transactionvalidator.model;

import java.util.HashMap;
import java.util.Map;

public class Balance {
    private Map<String, Double> balances = new HashMap<>();

    public Balance() {
        // Inicializando saldos para cada categoria
        balances.put("FOOD", 1000.00);
        balances.put("MEAL", 1000.00);
        balances.put("CASH", 1000.00);
    }

    public double getBalance(String category) {
        return balances.getOrDefault(category, 0.0);
    }

    public void reduceBalance(String category, double amount) {
        double currentBalance = getBalance(category);
        balances.put(category, currentBalance - amount);
    }
}
