package com.itv.service.impl;

/**
 * A special discount offer can be associated with {@link com.itv.conf.SKU#B}
 */
public class SpecialOfferB extends DefaultOffer {

    private static final int DISCOUNT_QUANTITY = 2;
    private static final int DISCOUNT_PRICE = 45;

    @Override
    public int discount() {
        int times = (int) Math.floor(quantity / DISCOUNT_QUANTITY);
        int reducedPrice = DISCOUNT_PRICE * times + item.getUnitPrice() * (quantity - times * 2);
        int diff = originalPrice() - reducedPrice;
        return times >= 1 ? diff : 0;
    }

}
