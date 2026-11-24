package com.warehouseoptimizer.warehouse_optimization_app.security.authentication;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.AccountRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.RoleRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Account;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public AccountUserDetailsService(
            AccountRepository accountRepository,
            RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        String roleName = roleRepository.findRoleNameById(account.getRoleId())
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Role not found for user: " + username));

        return User.builder()
                .username(account.getUsername())
                .password(account.getPasswordHash())
                .roles(roleName)
                .disabled(!account.isActive())
                .build();
    }
}