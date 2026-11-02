package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain;

import java.util.Objects;

public final class Account {
    private final Integer id;
    private String username;
    private String email;
    private String passwordHash;
    private boolean active;
    private Integer roleId;

    public Account(Integer id, String username, String email, String passwordHash,
                   boolean active, Integer roleId) {
        this.id = id; // null for a not-yet-persisted account
        this.username = Objects.requireNonNull(username, "username must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.passwordHash = Objects.requireNonNull(passwordHash, "passwordHash must not be null");
        this.active = active;
        this.roleId = Objects.requireNonNull(roleId, "roleId must not be null");
    }

    public static Account createNew(String username, String email, String passwordHash, Integer roleId) {
        return new Account(null, username, email, passwordHash, true, roleId);
    }

    public void edit(String username, String email) {
        this.username = Objects.requireNonNull(username, "username must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
    }

    public void activate() { this.active = true; }
    public void deactivate() { this.active = false; }

    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public boolean isActive() { return active; }
    public Integer getRoleId() { return roleId; }

    public static class AccountNotFoundException extends RuntimeException {
        public AccountNotFoundException(Integer accountId) {
            super("Account not found: " + accountId);
        }
    }

    public static class DuplicateEmailException extends RuntimeException {
        public DuplicateEmailException(String email) {
            super("An account with email '" + email + "' already exists");
        }
    }

    public static class RoleNotFoundException extends RuntimeException {
        public RoleNotFoundException(Integer roleId) {
            super("Role not found: " + roleId);
        }
    }
}
