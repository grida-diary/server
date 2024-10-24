package org.grida

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["org.grida"])
@ImportAutoConfiguration(classes = [FeignAutoConfiguration::class])
class AiClientConfig {

    @Bean
    fun openAiSecretKey(
        @Value("\${openai.secret-key}") secretKey: String
    ): String {
        return secretKey
    }
}
