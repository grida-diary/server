package io.wwan13.aiclient.image

import io.wwan13.aiclient.config.AiClientConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "OpenAiImageGeneration",
    url = "https://api.openai.com/v1/images/generations",
    configuration = [AiClientConfig::class]
)
interface ImageGenerateClient {

    @PostMapping
    fun generate(request: ImageGenerateRequest): ImageGenerateResponse
}
