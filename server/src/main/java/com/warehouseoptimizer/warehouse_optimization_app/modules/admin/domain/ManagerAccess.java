package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain;

import java.util.Objects;

public final class ManagerAccess {
    private final Integer accountId;
    private final boolean warehouseManagement;
    private final boolean storeManagement;

    public ManagerAccess(Integer accountId, boolean warehouseManagement, boolean storeManagement) {
        this.accountId = Objects.requireNonNull(accountId, "accountId must not be null");
        if (!warehouseManagement && !storeManagement) {
            throw new NoAccessSelectedException();
        }
        this.warehouseManagement = warehouseManagement;
        this.storeManagement = storeManagement;
    }

    public Integer getAccountId() { return accountId; }
    public boolean hasWarehouseManagement() { return warehouseManagement; }
    public boolean hasStoreManagement() { return storeManagement; }

    public static class NoAccessSelectedException extends RuntimeException {
        public NoAccessSelectedException() {
            super("Select warehouse-management access, store-management access, or both");
        }
    }

    public static class NotAManagerException extends RuntimeException {
        public NotAManagerException(Integer accountId) {
            super("Account " + accountId + " does not have the Manager role");
        }
    }
}
