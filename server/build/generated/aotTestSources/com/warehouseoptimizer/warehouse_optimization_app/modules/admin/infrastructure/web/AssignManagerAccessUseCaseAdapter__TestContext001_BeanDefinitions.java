package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in.AssignManagerAccessUseCase;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AssignManagerAccessUseCaseAdapter}.
 */
@Generated
public class AssignManagerAccessUseCaseAdapter__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'assignManagerAccessUseCaseAdapter'.
   */
  private static BeanInstanceSupplier<AssignManagerAccessUseCaseAdapter> getAssignManagerAccessUseCaseAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AssignManagerAccessUseCaseAdapter>forConstructor(AssignManagerAccessUseCase.class)
            .withGenerator((registeredBean, args) -> new AssignManagerAccessUseCaseAdapter(args.get(0)));
  }

  /**
   * Get the bean definition for 'assignManagerAccessUseCaseAdapter'.
   */
  public static BeanDefinition getAssignManagerAccessUseCaseAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AssignManagerAccessUseCaseAdapter.class);
    beanDefinition.setInstanceSupplier(getAssignManagerAccessUseCaseAdapterInstanceSupplier());
    return beanDefinition;
  }
}
