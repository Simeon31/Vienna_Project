package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;

import java.util.List;

/** Output port: the storage capability the warehouse use cases need. */
public interface WarehouseRepository {

    Warehouse save(Warehouse warehouse);

    List<Warehouse> findAll();
}