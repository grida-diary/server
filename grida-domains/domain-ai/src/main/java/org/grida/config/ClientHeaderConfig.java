package org.grida.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class ClientHeaderConfig {

    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String SECRET_KEY_KEY = "Authorization";
    private static final String SECRET_KEY_FORMAT = "Bearer %s";

    private final String secretKey;

    public ClientHeaderConfig(OpenAiProperties properties) {
        this.secretKey = properties.getSecretKey();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
            requestTemplate
                    .header(SECRET_KEY_KEY, String.format(SECRET_KEY_FORMAT, secretKey));
        };
    }
}
