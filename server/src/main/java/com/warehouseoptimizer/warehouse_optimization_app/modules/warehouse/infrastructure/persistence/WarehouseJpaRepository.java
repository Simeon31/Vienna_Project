package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface WarehouseJpaRepository extends JpaRepository<WarehouseEntity, Long> {
}
