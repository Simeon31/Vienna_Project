package com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.dtos;

public record WarehouseStorePair(
        Long storeId,
        Long warehouseId,
        int rank,
        double distanceKm) {
}
