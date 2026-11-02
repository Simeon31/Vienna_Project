package org.springframework.modulith.events.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.modulith.events.core.EventPublicationRegistry;

/**
 * Bean definitions for {@link StalenessMonitorConfiguration}.
 */
@Generated
public class StalenessMonitorConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'org.springframework.modulith.events.config.StalenessMonitorConfiguration'.
   */
  private static BeanInstanceSupplier<StalenessMonitorConfiguration> getStalenessMonitorConfigurationInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<StalenessMonitorConfiguration>forConstructor(EventPublicationRegistry.class, StalenessProperties.class)
            .withGenerator((registeredBean, args) -> new StalenessMonitorConfiguration(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'stalenessMonitorConfiguration'.
   */
  public static BeanDefinition getStalenessMonitorConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(StalenessMonitorConfiguration.class);
    beanDefinition.setInstanceSupplier(getStalenessMonitorConfigurationInstanceSupplier());
    return beanDefinition;
  }
}
