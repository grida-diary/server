package org.grida.chat

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "OpenAiChatApi",
    url = "https://api.openai.com/v1/chat/completions",
)
interface OpenAiChatApi {

    @PostMapping(headers = ["Content-Type=application/json"])
    fun chat(
        @RequestHeader("Authorization") secretKey: String,
        @RequestBody request: OpenAiChatRequest
    ): OpenAiChatResponse
}
