package org.springframework.boot.session.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * Bean definitions for {@link SessionAutoConfiguration}.
 */
@Generated
public class SessionAutoConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'sessionAutoConfiguration'.
   */
  public static BeanDefinition getSessionAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SessionAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(SessionAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link SessionAutoConfiguration.ServletSessionConfiguration}.
   */
  @Generated
  public static class ServletSessionConfiguration {
    /**
     * Get the bean definition for 'servletSessionConfiguration'.
     */
    public static BeanDefinition getServletSessionConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(SessionAutoConfiguration.ServletSessionConfiguration.class);
      beanDefinition.setInstanceSupplier(SessionAutoConfiguration.ServletSessionConfiguration::new);
      return beanDefinition;
    }

    /**
     * Bean definitions for {@link SessionAutoConfiguration.ServletSessionConfiguration.WarDeploymentConfiguration}.
     */
    @Generated
    public static class WarDeploymentConfiguration {
      /**
       * Get the bean definition for 'warDeploymentConfiguration'.
       */
      public static BeanDefinition getWarDeploymentConfigurationBeanDefinition() {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(SessionAutoConfiguration.ServletSessionConfiguration.WarDeploymentConfiguration.class);
        beanDefinition.setInstanceSupplier(SessionAutoConfiguration.ServletSessionConfiguration.WarDeploymentConfiguration::new);
        return beanDefinition;
      }

      /**
       * Get the bean instance supplier for 'warDeplomentSessionTimeout'.
       */
      private static BeanInstanceSupplier<SessionTimeout> getWarDeplomentSessionTimeoutInstanceSupplier(
          ) {
        return BeanInstanceSupplier.<SessionTimeout>forFactoryMethod(SessionAutoConfiguration.ServletSessionConfiguration.WarDeploymentConfiguration.class, "warDeplomentSessionTimeout", SessionProperties.class)
                .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.session.autoconfigure.SessionAutoConfiguration$ServletSessionConfiguration$WarDeploymentConfiguration", SessionAutoConfiguration.ServletSessionConfiguration.WarDeploymentConfiguration.class).warDeplomentSessionTimeout(args.get(0)));
      }

      /**
       * Get the bean definition for 'warDeplomentSessionTimeout'.
       */
      public static BeanDefinition getWarDeplomentSessionTimeoutBeanDefinition() {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(SessionTimeout.class);
        beanDefinition.setFactoryBeanName("org.springframework.boot.session.autoconfigure.SessionAutoConfiguration$ServletSessionConfiguration$WarDeploymentConfiguration");
        beanDefinition.setInstanceSupplier(getWarDeplomentSessionTimeoutInstanceSupplier());
        return beanDefinition;
      }

      /**
       * Get the bean instance supplier for 'cookieSerializer'.
       */
      private static BeanInstanceSupplier<DefaultCookieSerializer> getCookieSerializerInstanceSupplier(
          ) {
        return BeanInstanceSupplier.<DefaultCookieSerializer>forFactoryMethod(SessionAutoConfiguration.ServletSessionConfiguration.WarDeploymentConfiguration.class, "cookieSerializer", ObjectProvider.class)
                .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.session.autoconfigure.SessionAutoConfiguration$ServletSessionConfiguration$WarDeploymentConfiguration", SessionAutoConfiguration.ServletSessionConfiguration.WarDeploymentConfiguration.class).cookieSerializer(args.get(0)));
      }

      /**
       * Get the bean definition for 'cookieSerializer'.
       */
      public static BeanDefinition getCookieSerializerBeanDefinition() {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(DefaultCookieSerializer.class);
        beanDefinition.setFactoryBeanName("org.springframework.boot.session.autoconfigure.SessionAutoConfiguration$ServletSessionConfiguration$WarDeploymentConfiguration");
        beanDefinition.setInstanceSupplier(getCookieSerializerInstanceSupplier());
        return beanDefinition;
      }
    }

    /**
     * Bean definitions for {@link SessionAutoConfiguration.ServletSessionConfiguration.RememberMeServicesConfiguration}.
     */
    @Generated
    public static class RememberMeServicesConfiguration {
      /**
       * Get the bean definition for 'rememberMeServicesConfiguration'.
       */
      public static BeanDefinition getRememberMeServicesConfigurationBeanDefinition() {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(SessionAutoConfiguration.ServletSessionConfiguration.RememberMeServicesConfiguration.class);
        beanDefinition.setInstanceSupplier(SessionAutoConfiguration.ServletSessionConfiguration.RememberMeServicesConfiguration::new);
        return beanDefinition;
      }

      /**
       * Get the bean instance supplier for 'rememberMeServicesCookieSerializerCustomizer'.
       */
      private static BeanInstanceSupplier<DefaultCookieSerializerCustomizer> getRememberMeServicesCookieSerializerCustomizerInstanceSupplier(
          ) {
        return BeanInstanceSupplier.<DefaultCookieSerializerCustomizer>forFactoryMethod(SessionAutoConfiguration.ServletSessionConfiguration.RememberMeServicesConfiguration.class, "rememberMeServicesCookieSerializerCustomizer")
                .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.session.autoconfigure.SessionAutoConfiguration$ServletSessionConfiguration$RememberMeServicesConfiguration", SessionAutoConfiguration.ServletSessionConfiguration.RememberMeServicesConfiguration.class).rememberMeServicesCookieSerializerCustomizer());
      }

      /**
       * Get the bean definition for 'rememberMeServicesCookieSerializerCustomizer'.
       */
      public static BeanDefinition getRememberMeServicesCookieSerializerCustomizerBeanDefinition() {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(DefaultCookieSerializerCustomizer.class);
        beanDefinition.setFactoryBeanName("org.springframework.boot.session.autoconfigure.SessionAutoConfiguration$ServletSessionConfiguration$RememberMeServicesConfiguration");
        beanDefinition.setInstanceSupplier(getRememberMeServicesCookieSerializerCustomizerInstanceSupplier());
        return beanDefinition;
      }
    }
  }
}
