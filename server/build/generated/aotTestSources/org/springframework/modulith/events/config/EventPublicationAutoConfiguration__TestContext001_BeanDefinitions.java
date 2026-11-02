package org.springframework.modulith.events.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.env.Environment;
import org.springframework.modulith.events.core.DefaultEventPublicationRegistry;
import org.springframework.modulith.events.core.EventPublicationRepository;
import org.springframework.modulith.events.support.CompletionRegisteringAdvisor;
import org.springframework.modulith.events.support.PersistentApplicationEventMulticaster;

/**
 * Bean definitions for {@link EventPublicationAutoConfiguration}.
 */
@Generated
public class EventPublicationAutoConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'eventPublicationAutoConfiguration'.
   */
  public static BeanDefinition getEventPublicationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EventPublicationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(EventPublicationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'eventPublicationRegistry'.
   */
  private static BeanInstanceSupplier<DefaultEventPublicationRegistry> getEventPublicationRegistryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<DefaultEventPublicationRegistry>forFactoryMethod(EventPublicationAutoConfiguration.class, "eventPublicationRegistry", EventPublicationRepository.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.modulith.events.config.EventPublicationAutoConfiguration", EventPublicationAutoConfiguration.class).eventPublicationRegistry(args.get(0, EventPublicationRepository.class), args.get(1, ObjectProvider.class)));
  }

  /**
   * Get the bean definition for 'eventPublicationRegistry'.
   */
  public static BeanDefinition getEventPublicationRegistryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DefaultEventPublicationRegistry.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setFactoryBeanName("org.springframework.modulith.events.config.EventPublicationAutoConfiguration");
    beanDefinition.setInstanceSupplier(getEventPublicationRegistryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'applicationEventMulticaster'.
   */
  private static BeanInstanceSupplier<PersistentApplicationEventMulticaster> getApplicationEventMulticasterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<PersistentApplicationEventMulticaster>forFactoryMethod(EventPublicationAutoConfiguration.class, "applicationEventMulticaster", ObjectFactory.class, ObjectFactory.class)
            .withGenerator((registeredBean, args) -> EventPublicationAutoConfiguration.applicationEventMulticaster(args.get(0, ObjectFactory.class), args.get(1, ObjectFactory.class)));
  }

  /**
   * Get the bean definition for 'applicationEventMulticaster'.
   */
  public static BeanDefinition getApplicationEventMulticasterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EventPublicationAutoConfiguration.class);
    beanDefinition.setTargetType(PersistentApplicationEventMulticaster.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getApplicationEventMulticasterInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'completionRegisteringAdvisor'.
   */
  private static BeanInstanceSupplier<CompletionRegisteringAdvisor> getCompletionRegisteringAdvisorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<CompletionRegisteringAdvisor>forFactoryMethod(EventPublicationAutoConfiguration.class, "completionRegisteringAdvisor", ObjectFactory.class)
            .withGenerator((registeredBean, args) -> EventPublicationAutoConfiguration.completionRegisteringAdvisor(args.get(0, ObjectFactory.class)));
  }

  /**
   * Get the bean definition for 'completionRegisteringAdvisor'.
   */
  public static BeanDefinition getCompletionRegisteringAdvisorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EventPublicationAutoConfiguration.class);
    beanDefinition.setTargetType(CompletionRegisteringAdvisor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getCompletionRegisteringAdvisorInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'asyncPropertiesDefaulter'.
   */
  private static BeanInstanceSupplier<EventPublicationAutoConfiguration.AsyncPropertiesDefaulter> getAsyncPropertiesDefaulterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EventPublicationAutoConfiguration.AsyncPropertiesDefaulter>forFactoryMethod(EventPublicationAutoConfiguration.class, "asyncPropertiesDefaulter", Environment.class)
            .withGenerator((registeredBean, args) -> EventPublicationAutoConfiguration.asyncPropertiesDefaulter(args.get(0)));
  }

  /**
   * Get the bean definition for 'asyncPropertiesDefaulter'.
   */
  public static BeanDefinition getAsyncPropertiesDefaulterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EventPublicationAutoConfiguration.class);
    beanDefinition.setTargetType(EventPublicationAutoConfiguration.AsyncPropertiesDefaulter.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getAsyncPropertiesDefaulterInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link EventPublicationAutoConfiguration.AsyncEnablingConfiguration}.
   */
  @Generated
  public static class AsyncEnablingConfiguration {
    /**
     * Get the bean definition for 'asyncEnablingConfiguration'.
     */
    public static BeanDefinition getAsyncEnablingConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(EventPublicationAutoConfiguration.AsyncEnablingConfiguration.class);
      beanDefinition.setInstanceSupplier(EventPublicationAutoConfiguration.AsyncEnablingConfiguration::new);
      return beanDefinition;
    }
  }
}
