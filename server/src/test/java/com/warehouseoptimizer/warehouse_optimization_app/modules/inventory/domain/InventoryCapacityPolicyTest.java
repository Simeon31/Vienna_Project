package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.Product;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryCapacityPolicyTest {

    private final InventoryCapacityPolicy policy = new InventoryCapacityPolicy();

    @Test
    void acceptsProductsWithinWarehouseCapacity() {
        WarehouseCapacity warehouse = warehouseWithCapacity(1.01);
        ProductDemand productDemand = productDemand(10, 10, 10, 1000);

        assertTrue(policy.fits(warehouse, List.of(productDemand)));
    }

    @Test
    void rejectsProductsExceedingWarehouseCapacity() {
        WarehouseCapacity warehouse = warehouseWithCapacity(0.99);
        ProductDemand productDemand = productDemand(10, 10, 10, 1000);

        assertFalse(policy.fits(warehouse, List.of(productDemand)));
    }

    @Test
    void evaluatesTheCombinedVolumeOfAllProducts() {
        WarehouseCapacity warehouse = warehouseWithCapacity(1.49);
        ProductDemand first = productDemand(10, 10, 10, 1000);
        ProductDemand second = productDemand(10, 10, 10, 500);

        assertFalse(policy.fits(warehouse, List.of(first, second)));
    }

    @Test
    void rejectsMissingInputs() {
        assertThrows(IllegalArgumentException.class,
                () -> policy.fits(null, List.of()));
        assertThrows(IllegalArgumentException.class,
                () -> policy.requiredVolumeM3(Arrays.asList((ProductDemand) null)));
    }

    private ProductDemand productDemand(
            double lengthCm,
            double widthCm,
            double heightCm,
            float demandUnits) {
        Product product = new Product(
                1L,
                "SKU-001",
                "Test product",
                "Test",
                lengthCm,
                widthCm,
                heightCm,
                1.0);
        return new ProductDemand(product, new Demand(demandUnits));
    }

    private WarehouseCapacity warehouseWithCapacity(double capacity) {
        return new WarehouseCapacity(capacity);
    }
}
