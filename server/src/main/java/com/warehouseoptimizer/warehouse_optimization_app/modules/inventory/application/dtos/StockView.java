package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos;

/** Internal read result; not part of the inter-module API. */
public record StockView(
        String sku,
        String warehouseId,
        int onHand,
        int reserved,
        int availableQuantity
) {
}
