
package com.warehouseoptimizer.warehouse_optimization_app.modules.order.application.ports.out;

import com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain.Order;
import com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain.OrderStatus;

import java.util.List;

public interface OrderRepository {

    List<Order> findByStatus(OrderStatus status);

}
