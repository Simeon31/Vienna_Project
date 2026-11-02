package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.domain.Shipment;

/** Input port for checking stock while receiving a shipment. */
public interface CheckStockUseCase {

    boolean checkStock(Shipment shipment);
}
