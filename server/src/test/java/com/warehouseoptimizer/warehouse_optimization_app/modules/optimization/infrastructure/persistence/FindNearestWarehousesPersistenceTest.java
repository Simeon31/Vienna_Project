package com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out.StoreRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.Store;
import com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.dtos.WarehouseStorePair;
import com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.ports.in.FindNearestWarehousesUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out.WarehouseRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class FindNearestWarehousesPersistenceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private FindNearestWarehousesUseCase findNearestWarehouses;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Test
    void findsNearestActiveWarehouseUsingPersistedStoreAndWarehouseData() {
        Store store = storeRepository.save(
                new Store(null, "Optimization Test Store", "Test Address", "Vienna",
                        "test-warehouse", 10, 0, 0));
        Warehouse nearest = warehouseRepository.save(
                new Warehouse(null, "Optimization Test Warehouse", "Test Address",
                        0, 0.01, 100, true));
        warehouseRepository.save(
                new Warehouse(null, "Optimization Test Inactive Warehouse", "Test Address",
                        0, 0.01, 100, false));

        List<WarehouseStorePair> result = findNearestWarehouses.findNearest(store.id(), 1);

        assertEquals(List.of(nearest.id()), result.stream()
                .map(WarehouseStorePair::warehouseId)
                .toList());
        assertEquals(store.id(), result.get(0).storeId());
        assertEquals(1, result.get(0).rank());
    }
}
