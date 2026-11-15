package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {

    Store save(Store store);

    List<Store> findAll();

    Optional<Store> findById(Long id);
}
