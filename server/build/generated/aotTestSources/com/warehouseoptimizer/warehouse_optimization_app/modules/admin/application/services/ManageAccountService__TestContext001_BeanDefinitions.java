package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.AccountRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.RoleRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Bean definitions for {@link ManageAccountService}.
 */
@Generated
public class ManageAccountService__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'manageAccountService'.
   */
  private static BeanInstanceSupplier<ManageAccountService> getManageAccountServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ManageAccountService>forConstructor(AccountRepository.class, RoleRepository.class, PasswordEncoder.class)
            .withGenerator((registeredBean, args) -> new ManageAccountService(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'manageAccountService'.
   */
  public static BeanDefinition getManageAccountServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ManageAccountService.class);
    beanDefinition.setInstanceSupplier(getManageAccountServiceInstanceSupplier());
    return beanDefinition;
  }
}
