package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.in.ManageWarehouseUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out.WarehouseRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
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
}