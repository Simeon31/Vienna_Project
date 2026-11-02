package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.InventoryItem;

import java.util.Optional;

/** Output port: the storage capability needed by the use case. */
public interface InventoryRepository {
    Optional<InventoryItem> findBySkuAndWarehouseId(String sku, String warehouseId);
}
