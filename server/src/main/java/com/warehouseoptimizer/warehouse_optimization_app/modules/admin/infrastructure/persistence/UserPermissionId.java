package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import java.io.Serializable;
import java.util.Objects;

public class UserPermissionId implements Serializable {

    private Integer userId;
    private Integer permissionId;

    public UserPermissionId() {
    }

    public UserPermissionId(Integer userId, Integer permissionId) {
        this.userId = userId;
        this.permissionId = permissionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPermissionId that)) return false;

        return Objects.equals(userId, that.userId)
                && Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, permissionId);
    }
}