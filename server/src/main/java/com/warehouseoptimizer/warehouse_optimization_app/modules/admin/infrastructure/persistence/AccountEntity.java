package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    protected AccountEntity() {
    }

    public AccountEntity(Integer id, String username, String email, String passwordHash,
                         boolean active, Integer roleId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.active = active;
        this.roleId = roleId;
    }

    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public boolean isActive() { return active; }
    public Integer getRoleId() { return roleId; }
}
