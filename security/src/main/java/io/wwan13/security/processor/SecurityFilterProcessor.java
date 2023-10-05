package io.wwan13.security.processor;

import io.wwan13.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityFilterProcessor extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtFilter jwtFilter;

    HttpSecurity common(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
        return builder;
    }
}
