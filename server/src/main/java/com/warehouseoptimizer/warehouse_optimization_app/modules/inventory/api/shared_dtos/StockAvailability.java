package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.api.shared_dtos;

/**
 * Immutable snapshot for other backend modules.
 * Internal on-hand and reserved counts are deliberately not exposed.
 */
public record StockAvailability(Long productId, String warehouseId, int availableQuantity) {
}
