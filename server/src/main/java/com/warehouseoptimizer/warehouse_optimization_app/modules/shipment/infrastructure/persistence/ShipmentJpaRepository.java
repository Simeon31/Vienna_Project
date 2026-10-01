package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ShipmentJpaRepository extends JpaRepository<ShipmentEntity, Long> {

    boolean existsByWarehouseIdAndOrderStatusNot(Long warehouseId, String inactiveStatus);
}
