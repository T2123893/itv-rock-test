package com.itv.service.impl;

import java.util.MissingResourceException;

import com.itv.conf.SKU;
import com.itv.service.Offer;

/**
 * This is a default offer which implemented the {@link com.itv.service.Offer}. The responsibility of this
 * implementation only calculates original subtotal without discount. It can be extended by any other concrete special
 * offer implementation, which only needs to override {@link #discount()} in most cases.
 */
public class DefaultOffer implements Offer {

    protected SKU item;
    protected int quantity;

    @Override
    public Offer applyTo(SKU item) {
        this.item = item;
        return this;
    }

    @Override
    public Offer withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public int discount() {
        return 0;
    }

    @Override
    public int originalPrice() {
        if (this.item == null) {
            throw new MissingResourceException("Missing SKU", SKU.class.getName(), "");
        }

        return item.getUnitPrice() * quantity;
    }

    @Override
    public int finalPrice() {
        return originalPrice() - discount();
    }

}
