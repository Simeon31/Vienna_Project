package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.in.ManageWarehouseUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out.WarehouseRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.WarehouseNotFoundException;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.WarehouseHasActiveShipmentException;
import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.out.ShipmentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageWarehouseService implements ManageWarehouseUseCase {

    private final WarehouseRepository repository;
    private final ShipmentRepository shipmentRepository;

    public ManageWarehouseService(WarehouseRepository repository, ShipmentRepository shipmentRepository) {
        this.repository = repository;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Warehouse createWarehouse(String name, String address, double latitude, double longitude, double capacity) {
        Warehouse warehouse = new Warehouse(null, name, address, latitude, longitude, capacity, true);
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
    @Transactional
    public Warehouse deactivateWarehouse(Long id) {
        repository.findByIdForUpdate(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
        if (shipmentRepository.existsActiveByWarehouseId(id)) {
            throw new WarehouseHasActiveShipmentException(id);
        }
        return changeActiveStatus(id, false);
    }

    private Warehouse changeActiveStatus(Long id, boolean active) {
        Warehouse existing = repository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));

        Warehouse updated = new Warehouse(
                existing.id(),
                existing.name(),
                existing.address(),
                existing.latitude(),
                existing.longitude(),
                existing.capacity(),
                active);

        return repository.save(updated);
    }

    @Override
    public Warehouse updateWarehouse(Long id, String name, String address, double latitude, double longitude, double capacity) {
        Warehouse existing = repository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));

        Warehouse updated = new Warehouse(
                existing.id(),
                name,
                address,
                latitude,
                longitude,
                capacity,
                existing.active());

        return repository.save(updated);
    }
}