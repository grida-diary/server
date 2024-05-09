package org.grida.config;

import lombok.RequiredArgsConstructor;
import org.grida.interceptor.AuthenticationInterceptor;
import org.grida.interceptor.DecodeTokenInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
public class SecurityInterceptorRegistrar implements WebMvcConfigurer {

    private final DecodeTokenInterceptor decodeTokenInterceptor;
    private final AuthenticationInterceptor authenticationInterceptor;
    private final UserEmailResolver userEmailResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(decodeTokenInterceptor);
        registry.addInterceptor(authenticationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userEmailResolver);
    }
}
