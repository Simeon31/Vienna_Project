package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Permission;

import java.util.Optional;

public interface PermissionRepository {

    Optional<Permission> findByName(String name);
}