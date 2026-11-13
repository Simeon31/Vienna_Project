package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in.ManageAccountUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.AccountRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.RoleRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Account;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageAccountService implements ManageAccountUseCase {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ManageAccountService(AccountRepository accountRepository,
                                RoleRepository roleRepository,
                                PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account createAccount(String username, String email, String rawPassword, Integer roleId) {
        accountRepository.findByEmail(email).ifPresent(existing -> {
            throw new Account.DuplicateEmailException(email);
        });
        if (!roleRepository.existsById(roleId)) {
            throw new Account.RoleNotFoundException(roleId);
        }
        String passwordHash = passwordEncoder.encode(rawPassword);
        Account account = Account.createNew(username, email, passwordHash, roleId);
        return accountRepository.save(account);
    }

    @Override
    public Account editAccount(Integer accountId, String username, String email) {
        Account account = getAccountOrThrow(accountId);
        account.edit(username, email);
        return accountRepository.save(account);
    }

    @Override
    public Account activateAccount(Integer accountId) {
        Account account = getAccountOrThrow(accountId);
        account.activate();
        return accountRepository.save(account);
    }

    @Override
    public Account deactivateAccount(Integer accountId) {
        Account account = getAccountOrThrow(accountId);
        account.deactivate();
        return accountRepository.save(account);
    }

    @Override
    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }

    private Account getAccountOrThrow(Integer accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new Account.AccountNotFoundException(accountId));
    }
}
