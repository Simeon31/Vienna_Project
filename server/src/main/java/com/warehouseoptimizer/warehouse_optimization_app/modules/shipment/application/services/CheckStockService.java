package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.services;
import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.in.CheckStockUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.domain.Shipment;

public class CheckStockService implements CheckStockUseCase{

    @Override
    public boolean checkStock(Shipment shipment) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

}