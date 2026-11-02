package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

public final class InventoryItem {

    private final String sku;
    private final String warehouseId;
    private final int onHand;
    private final int reserved;

    public InventoryItem(String sku, String warehouseId, int onHand, int reserved) {
        if (sku == null || sku.isBlank() || warehouseId == null || warehouseId.isBlank()) {
            throw new IllegalArgumentException("SKU and warehouse ID are required");
        }
        if (onHand < 0 || reserved < 0 || reserved > onHand) {
            throw new IllegalArgumentException("Stock must satisfy 0 <= reserved <= onHand");
        }
        this.sku = sku;
        this.warehouseId = warehouseId;
        this.onHand = onHand;
        this.reserved = reserved;
    }

    public String sku() {
        return sku;
    }

    public String warehouseId() {
        return warehouseId;
    }

    public int onHand() {
        return onHand;
    }

    public int reserved() {
        return reserved;
    }
}
