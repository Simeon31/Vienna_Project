package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

public final class InventoryItem {

    private final Long productId;
    private final String warehouseId;
    private final Demand demand;
    private final StockLevel stock;

    public InventoryItem(Long productId, String warehouseId, Float demandInUnits, int onHand, int reserved) {
        if (productId == null || warehouseId == null || warehouseId.isBlank()) {
            throw new IllegalArgumentException("Product ID and warehouse ID are required");
        }
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.demand = new Demand(demandInUnits);
        this.stock = new StockLevel(onHand, reserved);
    }

    public String warehouseId() {
        return warehouseId;
    }

    public Long productId() {
        return productId;
    }

    public Float demandInUnits() {
        return demand.units();
    }

    public int onHand() {
        return stock.onHand();
    }

    public int reserved() {
        return stock.reserved();
    }

    public int availableQuantity() {
        return stock.availableQuantity();
    }
}
