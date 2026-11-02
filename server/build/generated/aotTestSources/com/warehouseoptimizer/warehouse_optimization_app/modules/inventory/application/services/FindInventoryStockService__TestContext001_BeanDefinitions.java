package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out.InventoryRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link FindInventoryStockService}.
 */
@Generated
public class FindInventoryStockService__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'findInventoryStockService'.
   */
  private static BeanInstanceSupplier<FindInventoryStockService> getFindInventoryStockServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<FindInventoryStockService>forConstructor(InventoryRepository.class)
            .withGenerator((registeredBean, args) -> new FindInventoryStockService(args.get(0)));
  }

  /**
   * Get the bean definition for 'findInventoryStockService'.
   */
  public static BeanDefinition getFindInventoryStockServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FindInventoryStockService.class);
    beanDefinition.setInstanceSupplier(getFindInventoryStockServiceInstanceSupplier());
    return beanDefinition;
  }
}
