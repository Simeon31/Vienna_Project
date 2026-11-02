package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.persistence;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link WarehouseRepositoryAdapter}.
 */
@Generated
public class WarehouseRepositoryAdapter__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'warehouseRepositoryAdapter'.
   */
  private static BeanInstanceSupplier<WarehouseRepositoryAdapter> getWarehouseRepositoryAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<WarehouseRepositoryAdapter>forConstructor(WarehouseJpaRepository.class)
            .withGenerator((registeredBean, args) -> new WarehouseRepositoryAdapter(args.get(0)));
  }

  /**
   * Get the bean definition for 'warehouseRepositoryAdapter'.
   */
  public static BeanDefinition getWarehouseRepositoryAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WarehouseRepositoryAdapter.class);
    beanDefinition.setInstanceSupplier(getWarehouseRepositoryAdapterInstanceSupplier());
    return beanDefinition;
  }
}
