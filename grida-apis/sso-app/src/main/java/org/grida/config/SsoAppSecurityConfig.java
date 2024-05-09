package org.grida.config;

import org.grida.annotation.EnableWebSecurity;
import org.grida.authorizedrequest.AuthorizedRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SsoAppSecurityConfig implements WebSecurityConfigurer {

    @Override
    public void addPatterns(AuthorizedRequest authorizedRequest) {
        authorizedRequest
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
