package io.wwan13.openai.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class OpenAiHeaderConfig {

    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String SECRET_KEY_KEY = "Authorization";
    private static final String SECRET_KEY_PREFIX = "Bearer ";

    private final String secretKey;

    public OpenAiHeaderConfig(@Value("${open-ai.secret-key}") String secretKey) {
        this.secretKey = SECRET_KEY_PREFIX + secretKey;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
            requestTemplate.header(SECRET_KEY_KEY, secretKey);
        };
    }
}
