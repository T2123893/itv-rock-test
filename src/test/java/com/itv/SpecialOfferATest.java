package com.itv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.itv.conf.SKU;
import com.itv.service.Offer;
import com.itv.service.impl.SpecialOfferA;

public class SpecialOfferATest {

    @Test
    public void testOfferADiscount() throws Exception {
        Offer offerA = new SpecialOfferA();
        offerA.applyTo(SKU.A);

        // 0 discount
        assertEquals(0, offerA.withQuantity(0).discount());
        assertEquals(0, offerA.withQuantity(1).discount());
        assertEquals(0, offerA.withQuantity(2).discount());

        // expected: unitPrice * quantity - reducedPrice - restOfPriceWithoutDiscount

        // 1 time discount: 3 -> 130
        assertEquals(SKU.A.getUnitPrice()*3 - 130, offerA.withQuantity(3).discount());
        assertEquals(SKU.A.getUnitPrice()*4 - 130 - 50, offerA.withQuantity(4).discount());
        assertEquals(SKU.A.getUnitPrice()*5 - 130 - 100, offerA.withQuantity(5).discount());

        // 2 times discount: 6 -> 260
        assertEquals(SKU.A.getUnitPrice()*6 - 260, offerA.withQuantity(6).discount());
        assertEquals(SKU.A.getUnitPrice()*7 - 260 - 50, offerA.withQuantity(7).discount());
        assertEquals(SKU.A.getUnitPrice()*8 - 260 - 100, offerA.withQuantity(8).discount());
    }
}
