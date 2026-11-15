package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(Integer id);
    Optional<Account> findByEmail(String email);
    List<Account> findAll();
}
