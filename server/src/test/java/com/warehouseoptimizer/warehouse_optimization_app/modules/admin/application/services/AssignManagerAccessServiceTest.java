package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.AccountRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.ManagerAccessRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.RoleRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Account;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.ManagerAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AssignManagerAccessServiceTest {

    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private ManagerAccessRepository managerAccessRepository;
    private AssignManagerAccessService service;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        roleRepository = mock(RoleRepository.class);
        managerAccessRepository = mock(ManagerAccessRepository.class);
        service = new AssignManagerAccessService(accountRepository, roleRepository, managerAccessRepository);
        when(managerAccessRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
    }

    private void givenAccountWithRole(int accountId, int roleId, String roleName) {
        when(accountRepository.findById(accountId))
                .thenReturn(Optional.of(new Account(accountId, "max", "max@example.com", "hash", true, roleId)));
        when(roleRepository.findRoleNameById(roleId)).thenReturn(Optional.of(roleName));
    }

    @Test
    void assignsWarehouseAccessOnly() {
        givenAccountWithRole(1, 2, "Manager");
        ManagerAccess result = service.assignManagerAccess(1, true, false);
        assertTrue(result.hasWarehouseManagement());
        assertFalse(result.hasStoreManagement());
    }

    @Test
    void assignsStoreAccessOnly() {
        givenAccountWithRole(1, 2, "Manager");
        ManagerAccess result = service.assignManagerAccess(1, false, true);
        assertFalse(result.hasWarehouseManagement());
        assertTrue(result.hasStoreManagement());
    }

    @Test
    void assignsBoth() {
        givenAccountWithRole(1, 2, "Manager");
        ManagerAccess result = service.assignManagerAccess(1, true, true);
        assertTrue(result.hasWarehouseManagement());
        assertTrue(result.hasStoreManagement());
    }

    @Test
    void rejectsWhenNothingSelected() {
        givenAccountWithRole(1, 2, "Manager");
        assertThrows(ManagerAccess.NoAccessSelectedException.class, () -> service.assignManagerAccess(1, false, false));
        verify(managerAccessRepository, never()).save(any());
    }

    @Test
    void rejectsNonManagerAccount() {
        givenAccountWithRole(1, 3, "Employee");
        assertThrows(ManagerAccess.NotAManagerException.class, () -> service.assignManagerAccess(1, true, false));
        verify(managerAccessRepository, never()).save(any());
    }

    @Test
    void rejectsUnknownAccount() {
        when(accountRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(Account.AccountNotFoundException.class, () -> service.assignManagerAccess(99, true, false));
    }
}
