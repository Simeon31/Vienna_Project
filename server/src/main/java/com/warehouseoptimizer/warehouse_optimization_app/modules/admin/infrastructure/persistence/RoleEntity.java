package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @Column(name = "role_id")
    private Integer id;

    @Column(nullable = false)
    private String rolename;

    protected RoleEntity() {
    }

    public Integer getId() { return id; }
    public String getRolename() { return rolename; }
}
