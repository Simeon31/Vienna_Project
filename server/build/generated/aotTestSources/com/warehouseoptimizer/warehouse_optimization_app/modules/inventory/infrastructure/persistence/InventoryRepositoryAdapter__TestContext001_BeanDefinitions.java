package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.persistence;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link InventoryRepositoryAdapter}.
 */
@Generated
public class InventoryRepositoryAdapter__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'inventoryRepositoryAdapter'.
   */
  private static BeanInstanceSupplier<InventoryRepositoryAdapter> getInventoryRepositoryAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<InventoryRepositoryAdapter>forConstructor(InventoryJpaRepository.class)
            .withGenerator((registeredBean, args) -> new InventoryRepositoryAdapter(args.get(0)));
  }

  /**
   * Get the bean definition for 'inventoryRepositoryAdapter'.
   */
  public static BeanDefinition getInventoryRepositoryAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InventoryRepositoryAdapter.class);
    beanDefinition.setInstanceSupplier(getInventoryRepositoryAdapterInstanceSupplier());
    return beanDefinition;
  }
}
