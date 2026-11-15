package com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out.StoreRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.Store;
import com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.dtos.WarehouseStorePair;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out.WarehouseRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindNearestWarehousesServiceTest {

    @Test
    void returnsActiveWarehousesInDistanceOrderWithRank() {
        StoreRepository stores = new InMemoryStoreRepository(
                new Store(7L, "Store", "Address", "Vienna", "legacy", 10, 48.2082, 16.3738));
        WarehouseRepository warehouses = new InMemoryWarehouseRepository(List.of(
                new Warehouse(1L, "Far", "Address", 48.8566, 2.3522, 100, true),
                new Warehouse(2L, "Near", "Address", 48.2100, 16.3750, 100, true),
                new Warehouse(3L, "Inactive", "Address", 48.2090, 16.3740, 100, false)));

        List<WarehouseStorePair> result =
                new FindNearestWarehousesService(stores, warehouses).findNearest(7L, 2);

        assertEquals(2L, result.get(0).warehouseId());
        assertEquals(1, result.get(0).rank());
        assertEquals(2, result.get(1).rank());
    }

    @Test
    void rejectsNonPositiveLimit() {
        StoreRepository stores = new InMemoryStoreRepository(null);
        WarehouseRepository warehouses = new InMemoryWarehouseRepository(List.of());

        assertThrows(IllegalArgumentException.class,
                () -> new FindNearestWarehousesService(stores, warehouses).findNearest(1L, 0));
    }

    private record InMemoryWarehouseRepository(List<Warehouse> warehouses) implements WarehouseRepository {
        @Override public Warehouse save(Warehouse warehouse) { return warehouse; }
        @Override public List<Warehouse> findAll() { return warehouses; }
        @Override public Optional<Warehouse> findById(Long id) {
            return warehouses.stream().filter(warehouse -> warehouse.id().equals(id)).findFirst();
        }
        @Override public Optional<Warehouse> findByIdForUpdate(Long id) { return findById(id); }
    }

    private record InMemoryStoreRepository(Store store) implements StoreRepository {
        @Override public Store save(Store value) { return value; }
        @Override public List<Store> findAll() { return store == null ? List.of() : List.of(store); }
        @Override public Optional<Store> findById(Long id) {
            return store != null && store.id().equals(id) ? Optional.of(store) : Optional.empty();
        }
    }
}
