package org.springframework.modulith.events.jackson;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;

/**
 * Bean definitions for {@link JacksonEventSerializationConfiguration}.
 */
@Generated
public class JacksonEventSerializationConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'org.springframework.modulith.events.jackson.JacksonEventSerializationConfiguration'.
   */
  private static BeanInstanceSupplier<JacksonEventSerializationConfiguration> getJacksonEventSerializationConfigurationInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JacksonEventSerializationConfiguration>forConstructor(ObjectProvider.class, ApplicationContext.class)
            .withGenerator((registeredBean, args) -> new JacksonEventSerializationConfiguration(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'jacksonEventSerializationConfiguration'.
   */
  public static BeanDefinition getJacksonEventSerializationConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JacksonEventSerializationConfiguration.class);
    beanDefinition.setInstanceSupplier(getJacksonEventSerializationConfigurationInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jacksonEventSerializer'.
   */
  private static BeanInstanceSupplier<JacksonEventSerializer> getJacksonEventSerializerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JacksonEventSerializer>forFactoryMethod(JacksonEventSerializationConfiguration.class, "jacksonEventSerializer")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("org.springframework.modulith.events.jackson.JacksonEventSerializationConfiguration", JacksonEventSerializationConfiguration.class).jacksonEventSerializer());
  }

  /**
   * Get the bean definition for 'jacksonEventSerializer'.
   */
  public static BeanDefinition getJacksonEventSerializerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JacksonEventSerializer.class);
    beanDefinition.setFactoryBeanName("org.springframework.modulith.events.jackson.JacksonEventSerializationConfiguration");
    beanDefinition.setInstanceSupplier(getJacksonEventSerializerInstanceSupplier());
    return beanDefinition;
  }
}
