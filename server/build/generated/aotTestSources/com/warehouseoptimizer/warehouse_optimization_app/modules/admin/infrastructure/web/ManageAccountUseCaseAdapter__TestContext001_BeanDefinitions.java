package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in.ManageAccountUseCase;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ManageAccountUseCaseAdapter}.
 */
@Generated
public class ManageAccountUseCaseAdapter__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'manageAccountUseCaseAdapter'.
   */
  private static BeanInstanceSupplier<ManageAccountUseCaseAdapter> getManageAccountUseCaseAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ManageAccountUseCaseAdapter>forConstructor(ManageAccountUseCase.class)
            .withGenerator((registeredBean, args) -> new ManageAccountUseCaseAdapter(args.get(0)));
  }

  /**
   * Get the bean definition for 'manageAccountUseCaseAdapter'.
   */
  public static BeanDefinition getManageAccountUseCaseAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ManageAccountUseCaseAdapter.class);
    beanDefinition.setInstanceSupplier(getManageAccountUseCaseAdapterInstanceSupplier());
    return beanDefinition;
  }
}
