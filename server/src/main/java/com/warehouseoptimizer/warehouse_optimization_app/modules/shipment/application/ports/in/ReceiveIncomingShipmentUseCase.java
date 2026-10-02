package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.domain.Shipment;

/** Input port for receiving incoming shipments. */
public interface ReceiveIncomingShipmentUseCase {

    Shipment receiveIncomingShipment(Shipment shipment);
}
