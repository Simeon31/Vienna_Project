package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionJpaRepository
        extends JpaRepository<UserPermissionEntity, UserPermissionId> {

    void deleteByUserId(Integer userId);
}