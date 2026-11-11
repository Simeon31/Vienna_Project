package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web.dto;

import java.util.List;

public class AssignPermissionsRequest {

    private List<String> permissions;

    public AssignPermissionsRequest() {
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}