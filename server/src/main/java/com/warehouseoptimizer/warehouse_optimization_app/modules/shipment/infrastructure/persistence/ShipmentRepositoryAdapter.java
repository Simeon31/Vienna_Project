package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.out.ShipmentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ShipmentRepositoryAdapter implements ShipmentRepository {

    private final ShipmentJpaRepository shipmentJpaRepository;

    public ShipmentRepositoryAdapter(ShipmentJpaRepository shipmentJpaRepository) {
        this.shipmentJpaRepository = shipmentJpaRepository;
    }

    @Override
    public boolean existsActiveByWarehouseId(Long warehouseId) {
        return shipmentJpaRepository
                .existsByWarehouseIdAndOrderStatusNot(warehouseId, "INACTIVE");
    }
}