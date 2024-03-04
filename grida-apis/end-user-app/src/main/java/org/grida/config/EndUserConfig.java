package org.grida.config;

import org.grida.authorizedrequest.AuthorizedRequest;
import org.grida.authorizedrequest.AuthorizedRequestBuilder;
import org.grida.filter.AuthFilterExceptionHandler;
import org.grida.filter.AuthenticationFilter;
import org.grida.filter.DecodeTokenFilter;
import org.grida.filter.LogFilter;
import org.grida.jwt.TokenDecoder;
import org.grida.jwt.TokenValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndUserConfig {

    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilterExceptionHandler> authFilterExceptionHandler() {
        FilterRegistrationBean<AuthFilterExceptionHandler> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilterExceptionHandler());
        registrationBean.setOrder(2);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<DecodeTokenFilter> decodeTokenFilter(
            TokenDecoder tokenDecoder,
            TokenValidator tokenValidator
    ) {
        FilterRegistrationBean<DecodeTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DecodeTokenFilter(tokenDecoder, tokenValidator));
        registrationBean.setOrder(3);

        return registrationBean;
    }

    @Bean
    public AuthorizedRequest authorizedRequest() {
        return AuthorizedRequestBuilder.withPatterns()
                .elseRequestPermit();
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authFilter(AuthorizedRequest authorizedRequest) {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter(authorizedRequest));
        registrationBean.setOrder(4);

        return registrationBean;
    }
}
