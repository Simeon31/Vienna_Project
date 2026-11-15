package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

public record Demand(Float units) {

    public Demand {
        if (units == null || units < 0) {
            throw new IllegalArgumentException("Demand must be greater than or equal to 0");
        }
    }
}
