package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in.AssignManagerAccessUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.AccountRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.ManagerAccessRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.RoleRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Account;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.ManagerAccess;
import org.springframework.stereotype.Service;

@Service
public class AssignManagerAccessService implements AssignManagerAccessUseCase {
    static final String MANAGER_ROLE = "manager";

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final ManagerAccessRepository managerAccessRepository;

    public AssignManagerAccessService(AccountRepository accountRepository,
                                      RoleRepository roleRepository,
                                      ManagerAccessRepository managerAccessRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.managerAccessRepository = managerAccessRepository;
    }

    @Override
    public ManagerAccess assignManagerAccess(Integer accountId, boolean warehouseManagement, boolean storeManagement) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new Account.AccountNotFoundException(accountId));
        String roleName = roleRepository.findRoleNameById(account.getRoleId())
                .orElseThrow(() -> new Account.RoleNotFoundException(account.getRoleId()));
        if (!MANAGER_ROLE.equalsIgnoreCase(roleName)) {
            throw new ManagerAccess.NotAManagerException(accountId);
        }
        ManagerAccess access = new ManagerAccess(accountId, warehouseManagement, storeManagement);
        return managerAccessRepository.save(access);
    }
}
