package org.grida.chat

import com.fasterxml.jackson.annotation.JsonProperty

data class OpenAiChatRequest(
    val model: String,
    val messages: List<OpenAiChatMessage>,
    @JsonProperty("response_format")
    val responseFormat: OpenAiResponseType
) {
    constructor(
        model: String,
        role: String,
        prompt: String,
        responseFormat: String
    ) : this(
        model,
        listOf(OpenAiChatMessage(role, prompt)),
        OpenAiResponseType(responseFormat)
    )
}

data class OpenAiResponseType(
    val type: String
)
