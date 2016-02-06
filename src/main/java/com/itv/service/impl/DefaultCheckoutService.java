package com.itv.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.itv.conf.AvailableOffer;
import com.itv.conf.SKU;
import com.itv.service.Checkout;

/**
 * A default implementation of Checkout service.
 */
public class DefaultCheckoutService implements Checkout {

    private final Map<SKU, Integer> scannedItems = new HashMap<>(SKU.values().length);

    @Override
    public int total() {
        if (scannedItems.size() == 0) {
            return 0;
        }

        return scannedItems.entrySet().stream()
                .map(this::subtotal)
                .reduce((sum, price) -> sum + price)
                .get();
    }

    @Override
    public boolean scan(SKU... items) {

        if (items == null || items.length == 0) {
            return false;
        }

        Arrays.asList(items).stream().forEach(this::process);

        return true;
    }

    private int subtotal(Entry<SKU, Integer> e) {
        return AvailableOffer.getInstance().get(e.getKey(), e.getValue()).finalPrice();
    }

    private void process(SKU item) {
        Integer quantity = scannedItems.get(item);
        if (quantity == null) {
            scannedItems.put(item, 1);
        } else {
            scannedItems.put(item, quantity + 1);
        }
    }

}
