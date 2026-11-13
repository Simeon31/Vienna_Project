
package com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldCreateValidOrder() {
        Order order = new Order(
                null,
                OrderStatus.PENDING,
                LocalDateTime.now()
        );

        assertEquals(OrderStatus.PENDING, order.getStatus());
        assertTrue(order.isPending());
    }

    @Test
    void shouldRejectMissingOrderStatus() {
        assertThrows(IllegalArgumentException.class, () ->
                new Order(
                        null,
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @Test
    void shouldIdentifyPendingOrder() {
        Order order = new Order(
                1L,
                OrderStatus.PENDING,
                LocalDateTime.now()
        );

        assertTrue(order.isPending());
    }
}
