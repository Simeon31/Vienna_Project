package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "manager_access")
public class ManagerAccessEntity {

    @Id
    @Column(name = "user_id")
    private Integer accountId;

    @Column(name = "warehouse_management", nullable = false)
    private boolean warehouseManagement;

    @Column(name = "store_management", nullable = false)
    private boolean storeManagement;

    protected ManagerAccessEntity() {
    }

    public ManagerAccessEntity(Integer accountId, boolean warehouseManagement, boolean storeManagement) {
        this.accountId = accountId;
        this.warehouseManagement = warehouseManagement;
        this.storeManagement = storeManagement;
    }

    public Integer getAccountId() { return accountId; }
    public boolean isWarehouseManagement() { return warehouseManagement; }
    public boolean isStoreManagement() { return storeManagement; }
}
