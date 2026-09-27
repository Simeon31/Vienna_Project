package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos.StockView;

import java.util.Optional;

/** Input port: what the inventory read use case offers its adapters. */
public interface FindInventoryStockUseCase {
    Optional<StockView> findStock(String sku, String warehouseId);
}
