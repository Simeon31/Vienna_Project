package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.PermissionRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PermissionRepositoryAdapter implements PermissionRepository {

    private final PermissionJpaRepository permissionJpaRepository;

    public PermissionRepositoryAdapter(PermissionJpaRepository permissionJpaRepository) {
        this.permissionJpaRepository = permissionJpaRepository;
    }

    @Override
    public Optional<Permission> findByName(String name) {
        return permissionJpaRepository.findByName(name)
                .map(entity -> new Permission(
                        entity.getId(),
                        entity.getName()
                ));
    }
}