package org.grida.config;

import org.grida.authorizedrequest.AuthorizedRequest;
import org.grida.authorizedrequest.AuthorizedRequestBuilder;
import org.grida.filter.AuthFilter;
import org.grida.filter.AuthFilterExceptionHandler;
import org.grida.filter.LogFilter;
import org.grida.jwt.TokenDecoder;
import org.grida.jwt.TokenValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import static org.grida.authorizedrequest.AuthorizedRequestBuilder.Support.*;

@Configuration
public class SsoConfig {

    @Bean
    public FilterRegistrationBean<AuthFilterExceptionHandler> authFilterExceptionHandler() {
        FilterRegistrationBean<AuthFilterExceptionHandler> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilterExceptionHandler());
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter(
            TokenDecoder tokenDecoder,
            TokenValidator tokenValidator
    ) {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthorizedRequest authorizedRequest = AuthorizedRequestBuilder.withPatterns()
                .antMatchers(
                        allHttpMethods(),
                        uriPatterns("/docs/**"),
                        permitAll()
                )
                .antMatchers(
                        httpMethods(HttpMethod.POST),
                        uriPatterns("/api/auth/login", "/api/auth/signup", "/api/auth/signup/email"),
                        permitAll()
                )
                .antMatchers(
                        httpMethods(HttpMethod.GET),
                        uriPatterns("/api/auth/role"),
                        authenticated()
                )
                .elseRequestAuthenticated();
        registrationBean.setFilter(new AuthFilter(tokenDecoder, tokenValidator, authorizedRequest));
        registrationBean.setOrder(2);

        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.setOrder(3);

        return registrationBean;
    }
}