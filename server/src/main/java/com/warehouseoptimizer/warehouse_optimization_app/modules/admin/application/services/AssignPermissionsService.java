package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in.AssignPermissionsUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.AccountRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.PermissionRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.RoleRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.UserPermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignPermissionsService implements AssignPermissionsUseCase {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserPermissionRepository userPermissionRepository;

    public AssignPermissionsService(
            AccountRepository accountRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            UserPermissionRepository userPermissionRepository
    ) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userPermissionRepository = userPermissionRepository;
    }

    @Override
    public void assignPermissions(Integer accountId, List<String> permissionNames) {

        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        String roleName = roleRepository.findRoleNameById(account.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        if (!roleName.equalsIgnoreCase("MANAGER")) {
            throw new IllegalArgumentException(
                    "Permissions can only be assigned to Managers"
            );
        }

        List<Integer> permissionIds = permissionNames.stream()
                .map(permissionName ->
                        permissionRepository.findByName(permissionName)
                                .orElseThrow(() ->
                                        new IllegalArgumentException(
                                                "Permission not found: " + permissionName
                                        )
                                )
                                .getId()
                )
                .toList();

        userPermissionRepository.replacePermissions(accountId, permissionIds);
    }
}