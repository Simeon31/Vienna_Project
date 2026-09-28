package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out.WarehouseRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WarehouseRepositoryAdapter implements WarehouseRepository {

    private final WarehouseJpaRepository jpaRepository;

    public WarehouseRepositoryAdapter(WarehouseJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        WarehouseEntity entity = toEntity(warehouse);
        WarehouseEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Warehouse> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private WarehouseEntity toEntity(Warehouse warehouse) {
        return new WarehouseEntity(
                warehouse.name(),
                warehouse.latitude(),
                warehouse.longitude(),
                warehouse.capacity(),
                warehouse.active());
    }

    private Warehouse toDomain(WarehouseEntity entity) {
        return new Warehouse(
                entity.getId(),
                entity.getName(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCapacity(),
                entity.isActive());
    }
}