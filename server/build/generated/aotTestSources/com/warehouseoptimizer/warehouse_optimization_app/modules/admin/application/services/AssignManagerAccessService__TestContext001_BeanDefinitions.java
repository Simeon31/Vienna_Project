package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.AccountRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.ManagerAccessRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.out.RoleRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AssignManagerAccessService}.
 */
@Generated
public class AssignManagerAccessService__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'assignManagerAccessService'.
   */
  private static BeanInstanceSupplier<AssignManagerAccessService> getAssignManagerAccessServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AssignManagerAccessService>forConstructor(AccountRepository.class, RoleRepository.class, ManagerAccessRepository.class)
            .withGenerator((registeredBean, args) -> new AssignManagerAccessService(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'assignManagerAccessService'.
   */
  public static BeanDefinition getAssignManagerAccessServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AssignManagerAccessService.class);
    beanDefinition.setInstanceSupplier(getAssignManagerAccessServiceInstanceSupplier());
    return beanDefinition;
  }
}
