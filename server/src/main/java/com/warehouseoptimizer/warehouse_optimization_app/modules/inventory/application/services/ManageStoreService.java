package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.in.ManageStoreUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out.StoreRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.Store;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageStoreService implements ManageStoreUseCase {

    private final StoreRepository repository;

    public ManageStoreService(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Store createStore(String name, String address, String city, String warehouseId, double capacityM3) {
        return repository.save(new Store(null, name, address, city, warehouseId, capacityM3));
    }

    @Override
    public List<Store> getAllStores() {
        return repository.findAll();
    }

    @Override
    public Optional<Store> getStoreById(Long id) {
        return repository.findById(id);
    }
}
