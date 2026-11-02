package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.in.CheckStockUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.in.ReceiveIncomingShipmentUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.out.ShipmentRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.domain.Shipment;

/**
 * Application service contract for receiving incoming shipments.
 *
 */
public class ReceiveIncomingShipmentService implements ReceiveIncomingShipmentUseCase {

    private final CheckStockUseCase checkStockUseCase;
    private final ShipmentRepository shipmentRepository;

    public ReceiveIncomingShipmentService(
            CheckStockUseCase checkStockUseCase,
            ShipmentRepository shipmentRepository) {
        this.checkStockUseCase = checkStockUseCase;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment receiveIncomingShipment(Shipment shipment) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
