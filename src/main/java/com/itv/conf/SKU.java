package com.itv.conf;

/**
 * A representation of Stock Keeping Units.
 * Goods to sell should register its item name and unit price in here.
 */
public enum SKU {
    A (50), B (30), C (20), D (15);

    private final int unitPrice;

    SKU(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}
