package com.caju.transactionvalidator.model;

import java.util.HashMap;
import java.util.Map;

public class Balance {

    private final Map<String, Double> balances = new HashMap<>();

    public Balance(double foodBalance, double mealBalance, double cashBalance) {
        balances.put("FOOD", foodBalance);
        balances.put("MEAL", mealBalance);
        balances.put("CASH", cashBalance);
    }

    public double getBalance(String category) {
        validateCategory(category);
        return balances.get(category);
    }

    public void reduceBalance(String category, double amount) {
        validateCategory(category);
        double currentBalance = balances.get(category);
        if (amount > currentBalance) {
            throw new IllegalArgumentException("Insufficient funds for " + category);
        }
        balances.put(category, currentBalance - amount);
    }

    private void validateCategory(String category) {
        if (!balances.containsKey(category)) {
            throw new IllegalArgumentException("Invalid category");
        }
    }
}
