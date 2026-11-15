package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StoreTest {

    @Test
    void storesAddressCityWarehouseAndCapacity() {
        Store store = new Store(1L, "Central Store", "Main Street 1", "Vienna", "WH-001", 42.5);

        assertEquals("Main Street 1", store.address());
        assertEquals("Vienna", store.city());
        assertEquals("WH-001", store.warehouseId());
        assertEquals(42.5, store.capacityM3());
    }

    @Test
    void rejectsNonPositiveCapacity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Store(1L, "Central Store", "Main Street 1", "Vienna", "WH-001", 0));
    }
}
