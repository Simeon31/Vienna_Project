package org.springframework.modulith.events.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.event.EventListenerFactory;
import org.springframework.core.env.Environment;
import org.springframework.modulith.events.EventExternalizationConfiguration;

/**
 * Bean definitions for {@link EventExternalizationAutoConfiguration}.
 */
@Generated
public class EventExternalizationAutoConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'eventExternalizationAutoConfiguration'.
   */
  public static BeanDefinition getEventExternalizationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EventExternalizationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(EventExternalizationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'filteringEventListenerFactory'.
   */
  private static BeanInstanceSupplier<EventListenerFactory> getFilteringEventListenerFactoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EventListenerFactory>forFactoryMethod(EventExternalizationAutoConfiguration.class, "filteringEventListenerFactory", EventExternalizationConfiguration.class)
            .withGenerator((registeredBean, args) -> EventExternalizationAutoConfiguration.filteringEventListenerFactory(args.get(0)));
  }

  /**
   * Get the bean definition for 'filteringEventListenerFactory'.
   */
  public static BeanDefinition getFilteringEventListenerFactoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EventExternalizationAutoConfiguration.class);
    beanDefinition.setTargetType(EventListenerFactory.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getFilteringEventListenerFactoryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'eventExternalizationConfiguration'.
   */
  private static BeanInstanceSupplier<EventExternalizationConfiguration> getEventExternalizationConfigurationInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EventExternalizationConfiguration>forFactoryMethod(EventExternalizationAutoConfiguration.class, "eventExternalizationConfiguration", BeanFactory.class, Environment.class)
            .withGenerator((registeredBean, args) -> EventExternalizationAutoConfiguration.eventExternalizationConfiguration(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'eventExternalizationConfiguration'.
   */
  public static BeanDefinition getEventExternalizationConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EventExternalizationAutoConfiguration.class);
    beanDefinition.setTargetType(EventExternalizationConfiguration.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getEventExternalizationConfigurationInstanceSupplier());
    return beanDefinition;
  }
}
