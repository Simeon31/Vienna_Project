package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.Store;

import java.util.List;
import java.util.Optional;

public interface ManageStoreUseCase {

    Store createStore(String name, String address, String city, String warehouseId,
                      double capacityM3, double latitude, double longitude);

    default Store createStore(String name, String address, String city, String warehouseId, double capacityM3) {
        return createStore(name, address, city, warehouseId, capacityM3, 0, 0);
    }

    List<Store> getAllStores();

    Optional<Store> getStoreById(Long id);
}
