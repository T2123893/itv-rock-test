package com.itv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.itv.conf.SKU;
import com.itv.service.impl.DefaultCheckoutService;
import com.itv.service.Checkout;

public class CheckoutTest {

    private Checkout checkout = new DefaultCheckoutService();

    @Test
    public void testScanAndCalculateTotalPriceOfSingleItems() throws Exception {
        int expectedTotal = 0;

        assertEquals(expectedTotal, checkout.total());

        assertTrue(checkout.scan(SKU.A));
        expectedTotal = expectedTotal + SKU.A.getUnitPrice();
        assertEquals(expectedTotal, checkout.total());

        assertTrue(checkout.scan(SKU.B));
        expectedTotal = expectedTotal + SKU.B.getUnitPrice();
        assertEquals(expectedTotal, checkout.total());

        assertTrue(checkout.scan(SKU.C));
        expectedTotal = expectedTotal + SKU.C.getUnitPrice();
        assertEquals(expectedTotal, checkout.total());

        assertTrue(checkout.scan(SKU.D));
        expectedTotal = expectedTotal + SKU.D.getUnitPrice();
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void testScanAndCalculateTotalPriceOf3Items_B_A_B() throws Exception {
        assertTrue(checkout.scan(SKU.B, SKU.A, SKU.B));

        // SKU.A 1 item => 50
        // SKU.B 2 items => 45
        assertEquals(95, checkout.total());
    }

    @Test
    public void testScanAndCalculateTotalPriceOf6Items_B_A_A_B_A_B() throws Exception {
        assertTrue(checkout.scan(SKU.B, SKU.A, SKU.A, SKU.B, SKU.A, SKU.B));

        // SKU.A 3 items => 130,
        // SKU.B 3 items => 45 + 30
        assertEquals(130 + 45 + 30, checkout.total());
    }

}
