package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Account;

import java.util.List;

public interface ManageAccountUseCase {
    Account createAccount(String username, String email, String rawPassword, Integer roleId);
    Account editAccount(Integer accountId, String username, String email);
    Account activateAccount(Integer accountId);
    Account deactivateAccount(Integer accountId);
    List<Account> listAccounts();
}
