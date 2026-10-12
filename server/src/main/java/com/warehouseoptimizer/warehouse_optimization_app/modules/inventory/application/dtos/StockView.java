package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos;

/** Internal read result; not part of the inter-module API. */
public record StockView(
        Long productId,
        String warehouseId,
        Float demandInUnits,
        int onHand,
        int reserved,
        int availableQuantity
) {
}
