package io.wwan13.processor;

import io.wwan13.jwt.JwtAccessDeniedHandler;
import io.wwan13.jwt.JwtAuthenticationEntryPointHandler;
import io.wwan13.jwt.JwtFilter;
import io.wwan13.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityFilterProcessor extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtFilter jwtFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPointHandler jwtAuthenticationEntryPointHandler;

    public HttpSecurity common(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler);
        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPointHandler);
        return httpSecurity;
    }
}
