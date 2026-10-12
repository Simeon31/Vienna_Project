package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

import java.util.Collection;

public final class InventoryCapacityPolicy {

    public boolean fits(StoreCapacity storeCapacity, Collection<ProductDemand> productDemands) {
        if (storeCapacity == null) {
            throw new IllegalArgumentException("Store capacity is required");
        }
        return requiredVolumeM3(productDemands) <= storeCapacity.volumeM3();
    }

    public double requiredVolumeM3(Collection<ProductDemand> productDemands) {
        if (productDemands == null) {
            throw new IllegalArgumentException("Product demands are required");
        }
        return productDemands.stream()
                .mapToDouble(productDemand -> {
                    if (productDemand == null) {
                        throw new IllegalArgumentException("Product demand is required");
                    }
                    return productDemand.requiredVolumeM3();
                })
                .sum();
    }

}
