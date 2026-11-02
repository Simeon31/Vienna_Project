package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web.dto;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.ManagerAccess;

public record ManagerAccessResponse(Integer accountId, boolean warehouseManagement, boolean storeManagement) {
    public static ManagerAccessResponse fromDomain(ManagerAccess access) {
        return new ManagerAccessResponse(access.getAccountId(), access.hasWarehouseManagement(),
                access.hasStoreManagement());
    }
}
