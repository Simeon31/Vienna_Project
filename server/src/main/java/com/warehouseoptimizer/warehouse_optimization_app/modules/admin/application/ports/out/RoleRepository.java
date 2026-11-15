package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out;

import java.util.Optional;

public interface RoleRepository {
    boolean existsById(Integer roleId);
}
