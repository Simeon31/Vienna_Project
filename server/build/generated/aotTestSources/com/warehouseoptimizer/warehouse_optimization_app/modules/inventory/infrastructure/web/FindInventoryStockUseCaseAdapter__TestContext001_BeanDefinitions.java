package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.in.FindInventoryStockUseCase;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link FindInventoryStockUseCaseAdapter}.
 */
@Generated
public class FindInventoryStockUseCaseAdapter__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'findInventoryStockUseCaseAdapter'.
   */
  private static BeanInstanceSupplier<FindInventoryStockUseCaseAdapter> getFindInventoryStockUseCaseAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<FindInventoryStockUseCaseAdapter>forConstructor(FindInventoryStockUseCase.class)
            .withGenerator((registeredBean, args) -> new FindInventoryStockUseCaseAdapter(args.get(0)));
  }

  /**
   * Get the bean definition for 'findInventoryStockUseCaseAdapter'.
   */
  public static BeanDefinition getFindInventoryStockUseCaseAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FindInventoryStockUseCaseAdapter.class);
    beanDefinition.setInstanceSupplier(getFindInventoryStockUseCaseAdapterInstanceSupplier());
    return beanDefinition;
  }
}
