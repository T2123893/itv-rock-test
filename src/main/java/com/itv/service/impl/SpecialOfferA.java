package com.itv.service.impl;

/**
 * A special discount offer which can be associated with {@link com.itv.conf.SKU#A}
 */
public class SpecialOfferA extends DefaultOffer {

    private static final int DISCOUNT_QUANTITY = 3;
    private static final int DISCOUNT_PRICE = 130;

    @Override
    public int discount() {
        int times = (int) Math.floor(quantity / DISCOUNT_QUANTITY);
        int reducedPrice = DISCOUNT_PRICE * times + item.getUnitPrice() * (quantity - times * 3);
        int diff = originalPrice() - reducedPrice;
        return times >= 1 ? diff : 0;
    }

}
