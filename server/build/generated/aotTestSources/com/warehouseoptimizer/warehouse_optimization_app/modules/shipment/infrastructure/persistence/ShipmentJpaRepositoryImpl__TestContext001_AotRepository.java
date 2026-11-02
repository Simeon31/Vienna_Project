package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link ShipmentJpaRepository}.
 */
@Generated
public class ShipmentJpaRepositoryImpl__TestContext001_AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ShipmentJpaRepositoryImpl__TestContext001_AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ShipmentJpaRepository#existsByWarehouseIdAndOrderStatusNot(java.lang.Long,java.lang.String)}.
   */
  public boolean existsByWarehouseIdAndOrderStatusNot(Long warehouseId, String inactiveStatus) {
    String queryString = "SELECT s.id FROM ShipmentEntity s WHERE s.warehouseId = :warehouseId AND s.orderStatus != :inactiveStatus";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("warehouseId", warehouseId);
    query.setParameter("inactiveStatus", inactiveStatus);
    query.setMaxResults(1);

    return !query.getResultList().isEmpty();
  }
}
