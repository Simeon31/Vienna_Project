package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "user_permission")
@IdClass(UserPermissionId.class)
public class UserPermissionEntity {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    protected UserPermissionEntity() {
    }

    public UserPermissionEntity(Integer userId, Integer permissionId) {
        this.userId = userId;
        this.permissionId = permissionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }
}