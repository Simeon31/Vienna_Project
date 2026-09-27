package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface InventoryJpaRepository extends JpaRepository<InventoryItemEntity, Long> {

    Optional<InventoryItemEntity> findBySkuAndWarehouseId(String sku, String warehouseId);
}
