package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out.WarehouseRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        return jpaRepository.findAll(Sort.by("id")).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Warehouse> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Warehouse> findByIdForUpdate(Long id) {
        return jpaRepository.findByIdForUpdate(id).map(this::toDomain);
    }

    private WarehouseEntity toEntity(Warehouse warehouse) {
        WarehouseEntity entity = new WarehouseEntity(
                warehouse.name(),
                warehouse.address(),
                warehouse.latitude(),
                warehouse.longitude(),
                warehouse.capacity(),
                warehouse.active());
        if(warehouse.id() != null) {
            entity.setId(warehouse.id());
        }
        return entity;
    }

    private Warehouse toDomain(WarehouseEntity entity) {
        return new Warehouse(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCapacity(),
                entity.isActive());
    }
}