
package com.warehouseoptimizer.warehouse_optimization_app.modules.order.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.order.application.ports.out.OrderRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain.Order;
import com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return orderJpaRepository.findByStatus(status)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private Order toDomain(OrderEntity entity) {
        return new Order(
                entity.getId(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
