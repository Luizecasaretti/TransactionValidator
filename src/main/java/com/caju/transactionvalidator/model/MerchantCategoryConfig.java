package com.caju.transactionvalidator.model;

import java.util.HashMap;
import java.util.Map;
public class MerchantCategoryConfig {
    private static final Map<String, String> merchantCategoryMap = new HashMap<>();

    static {
        merchantCategoryMap.put("UBER TRIP", "TRANSPORT");
        merchantCategoryMap.put("UBER EATS", "MEAL");
        merchantCategoryMap.put("PAG*JoseDaSilva", "FOOD");
        merchantCategoryMap.put("PICPAY*BILHETEUNICO", "TRANSPORT");
    }

    public static String getCategoryFromMerchant(String merchantName) {
        return merchantCategoryMap.getOrDefault(merchantName, null);
    }
}
