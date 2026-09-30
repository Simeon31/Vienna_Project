package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain;

public class WarehouseNotFoundException extends RuntimeException {
    public WarehouseNotFoundException(Long id) {
        super("Warehouse with id " + id + " not found!");
    }
}