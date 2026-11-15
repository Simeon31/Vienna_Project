package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository jpaRepository;

    public RoleRepositoryAdapter(RoleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsById(Integer roleId) {
        return jpaRepository.existsById(roleId);
    }

    @Override
    public Optional<String> findRoleNameById(Integer roleId) {
        return jpaRepository.findById(roleId)
                .map(RoleEntity::getRolename);
    }
}