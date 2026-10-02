package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain;

public class WarehouseHasActiveShipmentException extends RuntimeException {

    public WarehouseHasActiveShipmentException(Long warehouseId) {
        super("Warehouse with id " + warehouseId + " has an active shipment or order");
    }
}
