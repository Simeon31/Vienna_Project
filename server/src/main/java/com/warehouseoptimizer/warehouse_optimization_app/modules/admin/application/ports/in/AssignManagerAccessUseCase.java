package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.ManagerAccess;

public interface AssignManagerAccessUseCase {
    ManagerAccess assignManagerAccess(Integer accountId, boolean warehouseManagement, boolean storeManagement);
}
