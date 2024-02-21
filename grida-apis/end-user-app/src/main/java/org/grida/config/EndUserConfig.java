package org.grida.config;

import org.grida.authorizedrequest.AuthorizedRequest;
import org.grida.authorizedrequest.AuthorizedRequestBuilder;
import org.grida.filter.AuthFilter;
import org.grida.filter.LogFilter;
import org.grida.jwt.TokenDecoder;
import org.grida.jwt.TokenValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import static org.grida.authorizedrequest.AuthorizedRequestBuilder.Support.*;

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
    public AuthorizedRequest authorizedRequest() {
        return AuthorizedRequestBuilder.withPatterns()
                .elseRequestPermit();
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter(
            TokenDecoder tokenDecoder,
            TokenValidator tokenValidator,
            AuthorizedRequest authorizedRequest
    ) {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter(tokenDecoder, tokenValidator, authorizedRequest));
        registrationBean.setOrder(2);

        return registrationBean;
    }
}
