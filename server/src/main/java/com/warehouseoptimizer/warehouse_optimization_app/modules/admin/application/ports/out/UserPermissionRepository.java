package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out;

import java.util.List;

public interface UserPermissionRepository {

    void replacePermissions(Integer userId, List<Integer> permissionIds);
}