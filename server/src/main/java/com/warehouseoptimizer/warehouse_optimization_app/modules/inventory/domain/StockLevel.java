package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

public record StockLevel(int onHand, int reserved) {

    public StockLevel {
        if (onHand < 0 || reserved < 0 || reserved > onHand) {
            throw new IllegalArgumentException("Stock must satisfy 0 <= reserved <= onHand");
        }
    }

    public int availableQuantity() {
        return onHand - reserved;
    }
}
