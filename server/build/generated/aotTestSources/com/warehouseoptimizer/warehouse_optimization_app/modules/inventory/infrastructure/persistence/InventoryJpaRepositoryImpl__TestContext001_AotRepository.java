package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.Optional;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link InventoryJpaRepository}.
 */
@Generated
public class InventoryJpaRepositoryImpl__TestContext001_AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public InventoryJpaRepositoryImpl__TestContext001_AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link InventoryJpaRepository#findBySkuAndWarehouseId(java.lang.String,java.lang.String)}.
   */
  public Optional<InventoryItemEntity> findBySkuAndWarehouseId(String sku, String warehouseId) {
    String queryString = "SELECT i FROM InventoryItemEntity i WHERE i.sku = :sku AND i.warehouseId = :warehouseId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sku", sku);
    query.setParameter("warehouseId", warehouseId);

    return Optional.ofNullable((InventoryItemEntity) convertOne(query.getSingleResultOrNull(), false, InventoryItemEntity.class));
  }
}
