package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerAccessJpaRepository extends JpaRepository<ManagerAccessEntity, Integer> {
}
