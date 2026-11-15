
package com.warehouseoptimizer.warehouse_optimization_app.modules.order.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByStatus(OrderStatus status);

}
