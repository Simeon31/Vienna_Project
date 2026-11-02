package org.springdoc.core.configuration;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SpringDocSecurityConfiguration}.
 */
@Generated
public class SpringDocSecurityConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'springDocSecurityConfiguration'.
   */
  public static BeanDefinition getSpringDocSecurityConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SpringDocSecurityConfiguration.class);
    beanDefinition.setLazyInit(false);
    beanDefinition.setInstanceSupplier(SpringDocSecurityConfiguration::new);
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link SpringDocSecurityConfiguration.SpringDocSecurityOAuth2ClientConfiguration}.
   */
  @Generated
  public static class SpringDocSecurityOAuth2ClientConfiguration {
    /**
     * Get the bean instance supplier for 'org.springdoc.core.configuration.SpringDocSecurityConfiguration$SpringDocSecurityOAuth2ClientConfiguration'.
     */
    private static BeanInstanceSupplier<SpringDocSecurityConfiguration.SpringDocSecurityOAuth2ClientConfiguration> getSpringDocSecurityOAuthClientConfigurationInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<SpringDocSecurityConfiguration.SpringDocSecurityOAuth2ClientConfiguration>forConstructor(SpringDocSecurityConfiguration.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(SpringDocSecurityConfiguration.class).new SpringDocSecurityOAuth2ClientConfiguration());
    }

    /**
     * Get the bean definition for 'springDocSecurityOAuth2ClientConfiguration'.
     */
    public static BeanDefinition getSpringDocSecurityOAuthClientConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(SpringDocSecurityConfiguration.SpringDocSecurityOAuth2ClientConfiguration.class);
      beanDefinition.setLazyInit(false);
      beanDefinition.setInstanceSupplier(getSpringDocSecurityOAuthClientConfigurationInstanceSupplier());
      return beanDefinition;
    }
  }

  /**
   * Bean definitions for {@link SpringDocSecurityConfiguration.SpringSecurityLoginEndpointConfiguration}.
   */
  @Generated
  public static class SpringSecurityLoginEndpointConfiguration {
    /**
     * Get the bean instance supplier for 'org.springdoc.core.configuration.SpringDocSecurityConfiguration$SpringSecurityLoginEndpointConfiguration'.
     */
    private static BeanInstanceSupplier<SpringDocSecurityConfiguration.SpringSecurityLoginEndpointConfiguration> getSpringSecurityLoginEndpointConfigurationInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<SpringDocSecurityConfiguration.SpringSecurityLoginEndpointConfiguration>forConstructor(SpringDocSecurityConfiguration.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(SpringDocSecurityConfiguration.class).new SpringSecurityLoginEndpointConfiguration());
    }

    /**
     * Get the bean definition for 'springSecurityLoginEndpointConfiguration'.
     */
    public static BeanDefinition getSpringSecurityLoginEndpointConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(SpringDocSecurityConfiguration.SpringSecurityLoginEndpointConfiguration.class);
      beanDefinition.setLazyInit(false);
      beanDefinition.setInstanceSupplier(getSpringSecurityLoginEndpointConfigurationInstanceSupplier());
      return beanDefinition;
    }
  }

  /**
   * Bean definitions for {@link SpringDocSecurityConfiguration.SpringDocSecurityOAuth2Configuration}.
   */
  @Generated
  public static class SpringDocSecurityOAuth2Configuration {
    /**
     * Get the bean instance supplier for 'org.springdoc.core.configuration.SpringDocSecurityConfiguration$SpringDocSecurityOAuth2Configuration'.
     */
    private static BeanInstanceSupplier<SpringDocSecurityConfiguration.SpringDocSecurityOAuth2Configuration> getSpringDocSecurityOAuthConfigurationInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<SpringDocSecurityConfiguration.SpringDocSecurityOAuth2Configuration>forConstructor(SpringDocSecurityConfiguration.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(SpringDocSecurityConfiguration.class).new SpringDocSecurityOAuth2Configuration());
    }

    /**
     * Get the bean definition for 'springDocSecurityOAuth2Configuration'.
     */
    public static BeanDefinition getSpringDocSecurityOAuthConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(SpringDocSecurityConfiguration.SpringDocSecurityOAuth2Configuration.class);
      beanDefinition.setLazyInit(false);
      beanDefinition.setInstanceSupplier(getSpringDocSecurityOAuthConfigurationInstanceSupplier());
      return beanDefinition;
    }
  }
}
