package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;

import java.util.List;

/** Input port: what the warehouse management use case offers. */
public interface ManageWarehouseUseCase {

    Warehouse createWarehouse(String name, double latitude, double longitude, double capacity);

    List<Warehouse> getAllWarehouses();

    Warehouse activateWarehouse(Long id);

    Warehouse deactivateWarehouse(Long id);

    Warehouse updateWarehouse(Long id, String name, double latitude, double longitude, double capacity);
}