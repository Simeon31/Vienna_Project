package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

public record WarehouseCapacity(double volumeM3) {

    public WarehouseCapacity {
        if (!Double.isFinite(volumeM3) || volumeM3 <= 0) {
            throw new IllegalArgumentException("Warehouse capacity must be greater than 0");
        }
    }
}
