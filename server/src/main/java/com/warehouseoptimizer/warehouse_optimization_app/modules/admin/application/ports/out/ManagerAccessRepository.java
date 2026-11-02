package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.ManagerAccess;

public interface ManagerAccessRepository {
    ManagerAccess save(ManagerAccess managerAccess);
}
