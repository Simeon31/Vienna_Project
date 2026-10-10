package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<StoreEntity, Long> {
}
