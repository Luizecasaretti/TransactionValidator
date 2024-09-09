package com.caju.transactionvalidator.service;

import com.caju.transactionvalidator.model.Transaction;
import com.caju.transactionvalidator.model.Balance;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final Balance balance;

    public TransactionService() {
        this.balance = new Balance();
    }

    public String processTransaction(Transaction transaction) {
        String category = getCategoryFromMcc(transaction.getMcc());
        double currentBalance = balance.getBalance(category);

        if (transaction.getTotalAmount() > currentBalance) {
            return "{ \"code\": \"51\" }";
        } else {
            balance.reduceBalance(category, transaction.getTotalAmount());
            return "{ \"code\": \"00\" }";
        }
    }

    private String getCategoryFromMcc(String mcc) {
        switch (mcc) {
            case "5411":
            case "5412":
                return "FOOD";
            case "5811":
            case "5812":
                return "MEAL";
            default:
                return "CASH";
        }
    }
}
