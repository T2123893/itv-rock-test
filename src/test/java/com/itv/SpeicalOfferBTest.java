package com.itv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.itv.conf.SKU;
import com.itv.service.Offer;
import com.itv.service.impl.SpecialOfferB;

public class SpeicalOfferBTest {

    @Test
    public void testOfferBDiscount() throws Exception {
        Offer offerB = new SpecialOfferB();
        offerB.applyTo(SKU.B);

        // 0 discount
        assertEquals(0, offerB.withQuantity(0).discount());
        assertEquals(0, offerB.withQuantity(1).discount());

        // expected: unitPrice * quantity - reducedPrice - restOfPriceWithoutDiscount

        // 1 time discount: 2 -> 45
        assertEquals(SKU.B.getUnitPrice()*2 - 45, offerB.withQuantity(2).discount());
        assertEquals(SKU.B.getUnitPrice()*3 - 45 - 30, offerB.withQuantity(3).discount());

        // 2 times discount: 4 -> 90
        assertEquals(SKU.B.getUnitPrice()*4 - 90, offerB.withQuantity(4).discount());
        assertEquals(SKU.B.getUnitPrice()*5 - 90 - 30, offerB.withQuantity(5).discount());
    }
}
