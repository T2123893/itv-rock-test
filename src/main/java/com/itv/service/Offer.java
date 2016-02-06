package com.itv.service;

import com.itv.conf.SKU;

/**
 * Defines responsibility specification of an offer.
 * An offer implementation should be associating to a SKU item and able to offer a discount.
 */
public interface Offer {

    /**
     * Applies the offer to a SKU item.
     *
     * @param item a SKU item.
     * @return an offer instance.
     */
    Offer applyTo(SKU item);

    /**
     * Specify the SKU item quantity.
     *
     * @param quantity a quantity number of items.
     * @return an offer instance.
     */
    Offer withQuantity(int quantity);

    /**
     * Calculates a discount price based on SKU item and quantity.
     *
     * @return a discounted price.
     */
    int discount();

    /**
     * Calculates a subtotal price without discount.
     *
     * @return an full price.
     */
    int originalPrice();

    /**
     * Calculates a final price that actually needs to pay.
     *
     * @return a final total price to be paid
     */
    int finalPrice();

}
