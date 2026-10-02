package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.in.ReceiveIncomingShipmentUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.domain.Shipment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveIncomingShipmentController {

    private final ReceiveIncomingShipmentUseCase receiveIncomingShipmentUseCase;

    public ReceiveIncomingShipmentController(
            ReceiveIncomingShipmentUseCase receiveIncomingShipmentUseCase) {
        this.receiveIncomingShipmentUseCase = receiveIncomingShipmentUseCase;
    }

    @PostMapping("/api/receive_shipment")
    public Shipment receiveShipment(@RequestBody Shipment shipment) {
        return receiveIncomingShipmentUseCase.receiveIncomingShipment(shipment);
    }
}