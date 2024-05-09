package org.grida.annotation;

import org.grida.config.AuthorizedRequestRegistrar;
import org.grida.config.SecurityInterceptorRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({SecurityInterceptorRegistrar.class, AuthorizedRequestRegistrar.class})
public @interface EnableWebSecurity {
}
