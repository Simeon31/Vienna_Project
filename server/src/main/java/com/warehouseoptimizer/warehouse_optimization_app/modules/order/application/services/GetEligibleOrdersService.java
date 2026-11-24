
package com.warehouseoptimizer.warehouse_optimization_app.modules.order.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.order.application.ports.in.GetEligibleOrdersUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.order.application.ports.out.OrderRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain.Order;
import com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetEligibleOrdersService implements GetEligibleOrdersUseCase {

    private final OrderRepository orderRepository;

    public GetEligibleOrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getEligibleOrders() {
        return orderRepository.findByStatus(OrderStatus.PENDING);
    }
}
