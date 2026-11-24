package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.UserPermissionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserPermissionRepositoryAdapter implements UserPermissionRepository {

    private final UserPermissionJpaRepository userPermissionJpaRepository;

    public UserPermissionRepositoryAdapter(
            UserPermissionJpaRepository userPermissionJpaRepository
    ) {
        this.userPermissionJpaRepository = userPermissionJpaRepository;
    }

    @Override
    @Transactional
    public void replacePermissions(Integer userId, List<Integer> permissionIds) {

        userPermissionJpaRepository.deleteByUserId(userId);

        for (Integer permissionId : permissionIds) {
            UserPermissionEntity entity = new UserPermissionEntity(userId, permissionId);
            userPermissionJpaRepository.save(entity);
        }
    }
}