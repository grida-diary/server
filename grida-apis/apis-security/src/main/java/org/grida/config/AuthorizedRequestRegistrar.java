package org.grida.config;

import org.grida.authorizedrequest.AuthorizedRequest;
import org.springframework.context.annotation.Bean;

public class AuthorizedRequestRegistrar {

    private final WebSecurityConfigurer configurer;

    public AuthorizedRequestRegistrar(WebSecurityConfigurer configurer) {
        this.configurer = configurer;
    }

    @Bean
    public AuthorizedRequest authorizedRequest() {
        AuthorizedRequest authorizedRequest = AuthorizedRequest.of();
        configurer.addPatterns(authorizedRequest);
        return authorizedRequest;
    }
}
