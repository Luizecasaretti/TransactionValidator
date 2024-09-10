package com.caju.transactionvalidator.model;

public class Balance {

    private double foodBalance;
    private double mealBalance;
    private double cashBalance;

    public Balance(double foodBalance, double mealBalance, double cashBalance) {
        this.foodBalance = foodBalance;
        this.mealBalance = mealBalance;
        this.cashBalance = cashBalance;
    }

    public double getBalance(String category) {
        switch (category) {
            case "FOOD":
                return foodBalance;
            case "MEAL":
                return mealBalance;
            case "CASH":
                return cashBalance;
            default:
                throw new IllegalArgumentException("Invalid category");
        }
    }

    public void reduceBalance(String category, double amount) {
        switch (category) {
            case "FOOD":
                foodBalance -= amount;
                break;
            case "MEAL":
                mealBalance -= amount;
                break;
            case "CASH":
                cashBalance -= amount;
                break;
            default:
                throw new IllegalArgumentException("Invalid category");
        }
    }
}
