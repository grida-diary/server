package org.grida.image

import org.grida.config.AiClientConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "OpenAiImage",
    url = "https://api.openai.com/v1/images/generations",
    configuration = [AiClientConfig::class]
)
interface OpenAiImageApi {

    @PostMapping(headers = ["Content-Type=application/json"])
    fun generate(
        @RequestHeader("Authorization") secretKey: String,
        @RequestBody request: OpenAiImageRequest
    ): OpenAiImageResponse
}
