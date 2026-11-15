
package com.warehouseoptimizer.warehouse_optimization_app.modules.order.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain.Order;
import java.util.List;

public interface GetEligibleOrdersUseCase {

    List<Order> getEligibleOrders();

}
