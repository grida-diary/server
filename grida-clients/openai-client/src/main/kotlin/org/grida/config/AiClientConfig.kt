package org.grida.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(
    basePackages = [
        "org.grida.chat",
        "org.grida.image"
    ]
)
class AiClientConfig {

    @Bean
    fun openAiSecretKey(
        @Value("\${openai.secret-key}") secretKey: String
    ): String {
        return secretKey
    }
}
