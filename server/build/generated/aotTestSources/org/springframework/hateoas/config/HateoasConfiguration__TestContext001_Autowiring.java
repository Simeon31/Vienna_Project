package org.springframework.hateoas.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link HateoasConfiguration}.
 */
@Generated
public class HateoasConfiguration__TestContext001_Autowiring {
  /**
   * Apply the autowiring.
   */
  public static HateoasConfiguration apply(RegisteredBean registeredBean,
      HateoasConfiguration instance) {
    AutowiredFieldValueResolver.forRequiredField("context").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
