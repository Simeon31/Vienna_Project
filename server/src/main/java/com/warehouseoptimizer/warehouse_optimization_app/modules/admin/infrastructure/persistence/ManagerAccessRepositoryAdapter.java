package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.ManagerAccessRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.ManagerAccess;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerAccessRepositoryAdapter implements ManagerAccessRepository {

    private final ManagerAccessJpaRepository jpaRepository;

    public ManagerAccessRepositoryAdapter(ManagerAccessJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ManagerAccess save(ManagerAccess managerAccess) {
        ManagerAccessEntity saved = jpaRepository.save(new ManagerAccessEntity(
                managerAccess.getAccountId(),
                managerAccess.hasWarehouseManagement(),
                managerAccess.hasStoreManagement()));
        return new ManagerAccess(saved.getAccountId(), saved.isWarehouseManagement(), saved.isStoreManagement());
    }
}
