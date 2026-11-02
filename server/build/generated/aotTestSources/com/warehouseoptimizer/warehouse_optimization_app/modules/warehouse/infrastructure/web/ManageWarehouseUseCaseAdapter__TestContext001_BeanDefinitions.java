package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.in.ManageWarehouseUseCase;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ManageWarehouseUseCaseAdapter}.
 */
@Generated
public class ManageWarehouseUseCaseAdapter__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'manageWarehouseUseCaseAdapter'.
   */
  private static BeanInstanceSupplier<ManageWarehouseUseCaseAdapter> getManageWarehouseUseCaseAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ManageWarehouseUseCaseAdapter>forConstructor(ManageWarehouseUseCase.class)
            .withGenerator((registeredBean, args) -> new ManageWarehouseUseCaseAdapter(args.get(0)));
  }

  /**
   * Get the bean definition for 'manageWarehouseUseCaseAdapter'.
   */
  public static BeanDefinition getManageWarehouseUseCaseAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ManageWarehouseUseCaseAdapter.class);
    beanDefinition.setInstanceSupplier(getManageWarehouseUseCaseAdapterInstanceSupplier());
    return beanDefinition;
  }
}
