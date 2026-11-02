package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import java.util.Optional;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.query.Param;

/**
 * AOT generated JPA repository implementation for {@link WarehouseJpaRepository}.
 */
@Generated
public class WarehouseJpaRepositoryImpl__TestContext001_AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public WarehouseJpaRepositoryImpl__TestContext001_AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link WarehouseJpaRepository#findByIdForUpdate(java.lang.Long)}.
   */
  public Optional<WarehouseEntity> findByIdForUpdate(@Param("id") Long id) {
    String queryString = "select warehouse from WarehouseEntity warehouse where warehouse.id = :id";
    Query query = this.entityManager.createQuery(queryString);
    query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
    query.setParameter("id", id);

    return Optional.ofNullable((WarehouseEntity) convertOne(query.getSingleResultOrNull(), false, WarehouseEntity.class));
  }
}
