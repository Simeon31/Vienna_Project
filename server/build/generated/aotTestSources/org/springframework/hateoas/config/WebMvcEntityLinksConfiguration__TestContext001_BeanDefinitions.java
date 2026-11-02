package org.springframework.hateoas.config;

import java.lang.Class;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.ResolvableType;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.core.ControllerEntityLinksFactoryBean;
import org.springframework.hateoas.server.core.DelegatingEntityLinks;
import org.springframework.plugin.core.OrderAwarePluginRegistry;
import org.springframework.plugin.core.PluginRegistry;

/**
 * Bean definitions for {@link WebMvcEntityLinksConfiguration}.
 */
@Generated
public class WebMvcEntityLinksConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'webMvcEntityLinksConfiguration'.
   */
  public static BeanDefinition getWebMvcEntityLinksConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebMvcEntityLinksConfiguration.class);
    beanDefinition.setInstanceSupplier(WebMvcEntityLinksConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'webMvcEntityLinks'.
   */
  private static BeanInstanceSupplier<ControllerEntityLinksFactoryBean> getWebMvcEntityLinksInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ControllerEntityLinksFactoryBean>forFactoryMethod(WebMvcEntityLinksConfiguration.class, "webMvcEntityLinks", ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.hateoas.config.WebMvcEntityLinksConfiguration", WebMvcEntityLinksConfiguration.class).webMvcEntityLinks(args.get(0)));
  }

  /**
   * Get the bean definition for 'webMvcEntityLinks'.
   */
  public static BeanDefinition getWebMvcEntityLinksBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ControllerEntityLinksFactoryBean.class);
    beanDefinition.setFactoryBeanName("org.springframework.hateoas.config.WebMvcEntityLinksConfiguration");
    beanDefinition.setInstanceSupplier(getWebMvcEntityLinksInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'entityLinksPluginRegistry'.
   */
  private static BeanInstanceSupplier<OrderAwarePluginRegistry> getEntityLinksPluginRegistryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OrderAwarePluginRegistry>forFactoryMethod(WebMvcEntityLinksConfiguration.class, "entityLinksPluginRegistry", ListableBeanFactory.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.hateoas.config.WebMvcEntityLinksConfiguration", WebMvcEntityLinksConfiguration.class).entityLinksPluginRegistry(args.get(0)));
  }

  /**
   * Get the bean definition for 'entityLinksPluginRegistry'.
   */
  public static BeanDefinition getEntityLinksPluginRegistryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OrderAwarePluginRegistry.class);
    beanDefinition.setTargetType(ResolvableType.forClassWithGenerics(OrderAwarePluginRegistry.class, ResolvableType.forClass(EntityLinks.class), ResolvableType.forClass(Class.class)));
    beanDefinition.setFactoryBeanName("org.springframework.hateoas.config.WebMvcEntityLinksConfiguration");
    beanDefinition.setInstanceSupplier(getEntityLinksPluginRegistryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'delegatingEntityLinks'.
   */
  private static BeanInstanceSupplier<DelegatingEntityLinks> getDelegatingEntityLinksInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<DelegatingEntityLinks>forFactoryMethod(WebMvcEntityLinksConfiguration.class, "delegatingEntityLinks", PluginRegistry.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.hateoas.config.WebMvcEntityLinksConfiguration", WebMvcEntityLinksConfiguration.class).delegatingEntityLinks(args.get(0)));
  }

  /**
   * Get the bean definition for 'delegatingEntityLinks'.
   */
  public static BeanDefinition getDelegatingEntityLinksBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DelegatingEntityLinks.class);
    beanDefinition.setPrimary(true);
    beanDefinition.setFactoryBeanName("org.springframework.hateoas.config.WebMvcEntityLinksConfiguration");
    beanDefinition.setInstanceSupplier(getDelegatingEntityLinksInstanceSupplier());
    return beanDefinition;
  }
}
