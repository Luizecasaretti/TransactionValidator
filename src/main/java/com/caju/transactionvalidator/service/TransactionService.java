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
        try {

            String category = getCategoryFromMcc(transaction.getMcc());
            double currentBalance = balance.getBalance(category);

            if (transaction.getTotalAmount() <= currentBalance) {
                balance.reduceBalance(category, transaction.getTotalAmount());
                return "{ \"code\": \"00\" }"; // Transação aprovada
            } else {
                double cashBalance = balance.getBalance("CASH");
                if (transaction.getTotalAmount() <= cashBalance) {
                    balance.reduceBalance("CASH", transaction.getTotalAmount());
                    return "{ \"code\": \"00\" }";
                } else {
                    return "{ \"code\": \"51\" }";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{ \"code\": \"07\" }";
        }
    }

    private String getCategoryFromMcc(String mcc) {
        return switch (mcc) {
            case "5411", "5412" -> "FOOD";
            case "5811", "5812" -> "MEAL";
            default -> "CASH";
        };
    }
}
