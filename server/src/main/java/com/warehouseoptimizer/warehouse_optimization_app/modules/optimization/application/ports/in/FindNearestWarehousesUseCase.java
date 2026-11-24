package com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.dtos.WarehouseStorePair;

import java.util.List;

public interface FindNearestWarehousesUseCase {

    List<WarehouseStorePair> findNearest(Long storeId, int limit);
}
