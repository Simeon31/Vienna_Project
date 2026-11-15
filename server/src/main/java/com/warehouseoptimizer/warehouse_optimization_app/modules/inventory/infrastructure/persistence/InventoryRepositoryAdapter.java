package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out.InventoryRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.InventoryItem;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InventoryRepositoryAdapter implements InventoryRepository {

    private final InventoryJpaRepository jpaRepository;

    public InventoryRepositoryAdapter(InventoryJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<InventoryItem> findByProductIdAndWarehouseId(Long productId, String warehouseId) {
        return jpaRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                .map(item -> new InventoryItem(
                        item.getProductId(),
                        item.getWarehouseId(),
                        item.getDemandInUnits(),
                        item.getOnHand(),
                        item.getReserved()));
    }
}