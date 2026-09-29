package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.in.ManageWarehouseUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out.WarehouseRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.WarehouseNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageWarehouseService implements ManageWarehouseUseCase {

    private final WarehouseRepository repository;

    public ManageWarehouseService(WarehouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Warehouse createWarehouse(String name, double latitude, double longitude, double capacity) {
        Warehouse warehouse = new Warehouse(null, name, latitude, longitude, capacity, true);
        return repository.save(warehouse);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return repository.findAll();
    }

    @Override
    public Warehouse activateWarehouse(Long id) {
        return changeActiveStatus(id, true);
    }

    @Override
    public Warehouse deactivateWarehouse(Long id) {
        return changeActiveStatus(id, false);
    }

    private Warehouse changeActiveStatus(Long id, boolean active) {
        Warehouse existing = repository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));

        Warehouse updated = new Warehouse(
                existing.id(),
                existing.name(),
                existing.latitude(),
                existing.longitude(),
                existing.capacity(),
                active);

        return repository.save(updated);
    }

    @Override
    public Warehouse updateWarehouse(Long id, String name, double latitude, double longitude, double capacity) {
        Warehouse existing = repository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));

        Warehouse updated = new Warehouse(
                existing.id(),
                name,
                latitude,
                longitude,
                capacity,
                existing.active());

        return repository.save(updated);
    }
}