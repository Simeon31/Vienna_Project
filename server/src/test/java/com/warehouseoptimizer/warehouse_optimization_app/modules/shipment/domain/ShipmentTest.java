package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.domain;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentTest {

    @Test
    void shouldCreateValidShipment() {
        Shipment shipment = new Shipment(
                null,
                1L,
                "PENDING",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now()
        );

        assertEquals(1L, shipment.warehouseId());
        assertEquals("PENDING", shipment.orderStatus());
    }

    @Test
    void shouldRejectMissingWarehouseId() {
        assertThrows(IllegalArgumentException.class, () ->
                new Shipment(
                        null,
                        null,
                        "PENDING",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @Test
    void shouldRejectMissingOrderStatus() {
        assertThrows(IllegalArgumentException.class, () ->
                new Shipment(
                        null,
                        1L,
                        null,
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @Test
    void shouldRejectBlankOrderStatus() {
        assertThrows(IllegalArgumentException.class, () ->
                new Shipment(
                        null,
                        1L,
                        " ",
                        null,
                        LocalDateTime.now()
                )
        );
    }
}
