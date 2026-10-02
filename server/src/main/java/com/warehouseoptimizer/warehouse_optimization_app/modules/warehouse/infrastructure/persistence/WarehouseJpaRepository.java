package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface WarehouseJpaRepository extends JpaRepository<WarehouseEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select warehouse from WarehouseEntity warehouse where warehouse.id = :id")
    java.util.Optional<WarehouseEntity> findByIdForUpdate(@Param("id") Long id);
}
