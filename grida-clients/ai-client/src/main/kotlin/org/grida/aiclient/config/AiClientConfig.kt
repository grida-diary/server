package org.grida.aiclient.config

import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan
class AiClientConfig(
    @Value("\${openai.secret-key}") val secretKey: String
) {

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor {
            it.apply {
                header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
                header(SECRET_KEY_KEY, "Bearer $secretKey")
            }
        }
    }

    companion object {
        private const val CONTENT_TYPE_KEY = "Content-Type"
        private const val CONTENT_TYPE_VALUE = "application/json"
        private const val SECRET_KEY_KEY = "Authorization"
    }
}
