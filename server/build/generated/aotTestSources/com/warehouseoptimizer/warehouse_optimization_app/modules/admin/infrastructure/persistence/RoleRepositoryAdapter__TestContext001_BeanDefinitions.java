package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RoleRepositoryAdapter}.
 */
@Generated
public class RoleRepositoryAdapter__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'roleRepositoryAdapter'.
   */
  private static BeanInstanceSupplier<RoleRepositoryAdapter> getRoleRepositoryAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RoleRepositoryAdapter>forConstructor(RoleJpaRepository.class)
            .withGenerator((registeredBean, args) -> new RoleRepositoryAdapter(args.get(0)));
  }

  /**
   * Get the bean definition for 'roleRepositoryAdapter'.
   */
  public static BeanDefinition getRoleRepositoryAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RoleRepositoryAdapter.class);
    beanDefinition.setInstanceSupplier(getRoleRepositoryAdapterInstanceSupplier());
    return beanDefinition;
  }
}
