package io.wwan13.imagegenerate.processor.openai.config;

import feign.RequestInterceptor;
import io.wwan13.imagegenerate.config.OpenAiProperties;
import org.springframework.context.annotation.Bean;

public class OpenAiHeaderConfig {

    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String SECRET_KEY_KEY = "Authorization";
    private static final String SECRET_KEY_FORMAT = "Bearer %s";

    private final OpenAiProperties properties;

    public OpenAiHeaderConfig(OpenAiProperties properties) {
        this.properties = properties;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
            requestTemplate
                    .header(SECRET_KEY_KEY, String.format(SECRET_KEY_FORMAT, properties.getSecretKey()));
        };
    }
}
