package io.wwan13.aiclient.chat

import io.wwan13.aiclient.config.AiClientConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "OpenAiChatCompletion",
    url = "https://api.openai.com/v1/chat/completions",
    configuration = [AiClientConfig::class],
)
interface ChatCompletionClient {
    @PostMapping
    fun chat(chatCompletionRequest: ChatCompletionRequest): ChatCompletionResponse
}
