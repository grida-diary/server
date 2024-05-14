package org.grida.config;

import org.grida.authorizedrequest.AuthorizedRequest;
import org.grida.authorizedrequest.AuthorizedRequestApplier;
import org.grida.authorizedrequest.AuthorizedRequestFactory;
import org.springframework.context.annotation.Bean;

public class AuthorizedRequestRegistrar {

    private final WebSecurityConfigurer configurer;

    public AuthorizedRequestRegistrar(WebSecurityConfigurer configurer) {
        this.configurer = configurer;
    }

    @Bean
    public AuthorizedRequest authorizedRequest() {
        AuthorizedRequestFactory factory = AuthorizedRequestFactory.of();
        configurer.addPatterns(factory);
        return AuthorizedRequestApplier.apply(factory);
    }
}
