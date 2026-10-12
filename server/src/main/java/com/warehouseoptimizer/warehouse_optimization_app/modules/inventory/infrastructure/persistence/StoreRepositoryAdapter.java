package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out.StoreRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.Store;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StoreRepositoryAdapter implements StoreRepository {

    private final StoreJpaRepository repository;
    private final StoreEntityMapper mapper;

    public StoreRepositoryAdapter(StoreJpaRepository repository, StoreEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Store save(Store store) {
        return mapper.toDomain(repository.save(mapper.toEntity(store)));
    }

    @Override
    public List<Store> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Store> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
