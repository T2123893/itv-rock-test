package com.itv.service;

import com.itv.conf.SKU;

/**
 * Defines {@link com.itv.service.Checkout} behaviours.
 */
public interface Checkout {

    /**
     * Calculates the final price of scanned items.
     * @return the final total price that needs to be paid.
     */
    int total();

    /**
     * Scans SKU items into scanned item list.
     * @return true if successfully scanned.
     */
    boolean scan(SKU... item);

}
