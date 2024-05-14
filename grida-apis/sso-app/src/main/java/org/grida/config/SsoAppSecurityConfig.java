package org.grida.config;

import org.grida.annotation.EnableWebSecurity;
import org.grida.authorizedrequest.AuthorizedRequest;
import org.grida.authorizedrequest.AuthorizedRequestFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SsoAppSecurityConfig implements WebSecurityConfigurer {

    @Override
    public void addPatterns(AuthorizedRequestFactory factory) {
        factory
                .uriPatterns("/docs/**")
                .allHttpMethods()
                .permitAll()

                .uriPatterns("/api/auth/login", "/api/auth/signup", "/api/auth/signup/email")
                .httpMethodPost()
                .permitAll()

                .uriPatterns("/api/auth/role")
                .httpMethodGet()
                .authenticated()

                .elseRequestAuthenticated();
    }
}
