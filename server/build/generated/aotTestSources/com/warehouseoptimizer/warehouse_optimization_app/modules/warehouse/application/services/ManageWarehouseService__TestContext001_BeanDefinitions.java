package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.application.ports.out.ShipmentRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out.WarehouseRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ManageWarehouseService}.
 */
@Generated
public class ManageWarehouseService__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'manageWarehouseService'.
   */
  private static BeanInstanceSupplier<ManageWarehouseService> getManageWarehouseServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ManageWarehouseService>forConstructor(WarehouseRepository.class, ShipmentRepository.class)
            .withGenerator((registeredBean, args) -> new ManageWarehouseService(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'manageWarehouseService'.
   */
  public static BeanDefinition getManageWarehouseServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ManageWarehouseService.class);
    beanDefinition.setInstanceSupplier(getManageWarehouseServiceInstanceSupplier());
    return beanDefinition;
  }
}
