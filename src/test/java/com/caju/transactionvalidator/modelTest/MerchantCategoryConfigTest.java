package com.caju.transactionvalidator.modelTest;

import org.junit.jupiter.api.Test;
import com.caju.transactionvalidator.model.MerchantCategoryConfig;

import static org.junit.jupiter.api.Assertions.*;

class MerchantCategoryConfigTest {

    @Test
    void testGetCategoryFromMerchant_ExistingMerchant() {
        String category = MerchantCategoryConfig.getCategoryFromMerchant("UBER TRIP");
        assertEquals("TRANSPORT", category);

        category = MerchantCategoryConfig.getCategoryFromMerchant("UBER EATS");
        assertEquals("MEAL", category);

        category = MerchantCategoryConfig.getCategoryFromMerchant("PAG*JoseDaSilva");
        assertEquals("FOOD", category);

        category = MerchantCategoryConfig.getCategoryFromMerchant("PICPAY*BILHETEUNICO");
        assertEquals("TRANSPORT", category);
    }

    @Test
    void testGetCategoryFromMerchant_NonExistingMerchant() {
        String category = MerchantCategoryConfig.getCategoryFromMerchant("UNKNOWN MERCHANT");
        assertNull(category);
    }
}
