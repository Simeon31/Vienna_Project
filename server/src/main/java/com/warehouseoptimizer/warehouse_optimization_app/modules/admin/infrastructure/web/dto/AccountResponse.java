package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web.dto;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Account;

public record AccountResponse(Integer id, String username, String email, boolean active, Integer roleId) {
    public static AccountResponse fromDomain(Account account) {
        return new AccountResponse(account.getId(), account.getUsername(), account.getEmail(),
                account.isActive(), account.getRoleId());
    }
}
