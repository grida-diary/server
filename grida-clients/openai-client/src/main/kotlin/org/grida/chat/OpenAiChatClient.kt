package org.grida.chat

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class OpenAiChatClient(
    private val openAiChatApi: OpenAiChatApi,
    private val openAiSecretKey: String,
    private val objectMapper: ObjectMapper
) {

    fun <T> chat(
        prompt: String,
        valueType: Class<T>
    ): T {
        val bearerToken = "Bearer $openAiSecretKey"
        val request = OpenAiChatRequest(
            model = "gpt-4o",
            role = "system",
            prompt = prompt,
            responseFormat = "json_object"
        )

        val response = openAiChatApi.chat(bearerToken, request)
        val result = response.result

        return objectMapper.readValue(result, valueType)
    }
}
