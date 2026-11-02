package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.api.shared_interfaces;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.api.shared_dtos.StockAvailability;

import java.util.Optional;

/** Public backend contract. Empty means unknown stock; zero availability means known but depleted. */
public interface InventoryAvailability {
    Optional<StockAvailability> findAvailability(String sku, String warehouseId);
}
