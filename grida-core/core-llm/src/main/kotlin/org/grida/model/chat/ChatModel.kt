package org.grida.model.chat

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.stereotype.Component

@Component
class ChatModel(
    private val openAiChatModel: OpenAiChatModel,
    private val objectMapper: ObjectMapper,
) {

    fun <T> call(content: String, clazz: Class<T>): T {
        val prompt = Prompt(
            content,
            OpenAiChatOptions.builder()
                .withModel("gpt-4o")
                .build()
        )
        val response = openAiChatModel.call(prompt)

        val rawResult = response.result.output.content
        println(rawResult)
        return objectMapper.readValue(rawResult, clazz)
    }
}
