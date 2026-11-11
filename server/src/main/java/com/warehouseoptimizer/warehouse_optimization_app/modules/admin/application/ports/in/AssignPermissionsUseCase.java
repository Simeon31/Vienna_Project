package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in;

import java.util.List;

public interface AssignPermissionsUseCase {

    void assignPermissions(Integer accountId, List<String> permissionNames);
}

