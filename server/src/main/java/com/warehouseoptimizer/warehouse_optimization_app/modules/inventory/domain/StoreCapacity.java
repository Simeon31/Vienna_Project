package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

public record StoreCapacity(double volumeM3) {

    public StoreCapacity {
        if (!Double.isFinite(volumeM3) || volumeM3 <= 0) {
            throw new IllegalArgumentException("Store capacity must be greater than 0 cubic meters");
        }
    }
}
