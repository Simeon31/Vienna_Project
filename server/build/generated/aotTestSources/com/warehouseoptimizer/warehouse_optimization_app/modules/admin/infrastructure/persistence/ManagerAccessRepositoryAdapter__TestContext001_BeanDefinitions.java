package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ManagerAccessRepositoryAdapter}.
 */
@Generated
public class ManagerAccessRepositoryAdapter__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'managerAccessRepositoryAdapter'.
   */
  private static BeanInstanceSupplier<ManagerAccessRepositoryAdapter> getManagerAccessRepositoryAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ManagerAccessRepositoryAdapter>forConstructor(ManagerAccessJpaRepository.class)
            .withGenerator((registeredBean, args) -> new ManagerAccessRepositoryAdapter(args.get(0)));
  }

  /**
   * Get the bean definition for 'managerAccessRepositoryAdapter'.
   */
  public static BeanDefinition getManagerAccessRepositoryAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ManagerAccessRepositoryAdapter.class);
    beanDefinition.setInstanceSupplier(getManagerAccessRepositoryAdapterInstanceSupplier());
    return beanDefinition;
  }
}
