package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AccountRepositoryAdapter}.
 */
@Generated
public class AccountRepositoryAdapter__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'accountRepositoryAdapter'.
   */
  private static BeanInstanceSupplier<AccountRepositoryAdapter> getAccountRepositoryAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AccountRepositoryAdapter>forConstructor(AccountJpaRepository.class)
            .withGenerator((registeredBean, args) -> new AccountRepositoryAdapter(args.get(0)));
  }

  /**
   * Get the bean definition for 'accountRepositoryAdapter'.
   */
  public static BeanDefinition getAccountRepositoryAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AccountRepositoryAdapter.class);
    beanDefinition.setInstanceSupplier(getAccountRepositoryAdapterInstanceSupplier());
    return beanDefinition;
  }
}
