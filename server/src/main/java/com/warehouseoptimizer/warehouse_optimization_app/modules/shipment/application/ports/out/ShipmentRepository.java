package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.out;

public interface ShipmentRepository {

    boolean existsActiveByWarehouseId(Long warehouseId);
}
