package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.RoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryAdapter implements RoleRepository{
    private final RoleJpaRepository jpaRepository;

    public RoleRepositoryAdapter(RoleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsById(Integer roleId) {
        return jpaRepository.existsById(roleId);
    }
}
