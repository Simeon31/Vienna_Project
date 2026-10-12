package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos;

public record StoreResponse(
        Long id,
        String name,
        String address,
        String city,
        String warehouseId,
        double capacityM3) {
}
