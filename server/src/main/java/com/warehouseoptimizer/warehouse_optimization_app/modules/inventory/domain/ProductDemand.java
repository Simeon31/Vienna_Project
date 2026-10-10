package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.Product;

public record ProductDemand(Product product, Demand demand) {

    public ProductDemand {
        if (product == null) {
            throw new IllegalArgumentException("Product is required");
        }
        if (demand == null) {
            throw new IllegalArgumentException("Demand is required");
        }
    }

    public double requiredVolumeM3() {
        return product.volumeM3() * demand.units();
    }
}
