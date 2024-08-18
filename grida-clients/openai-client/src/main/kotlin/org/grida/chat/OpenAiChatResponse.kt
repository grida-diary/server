package org.grida.chat

data class OpenAiChatResponse(
    val choices: List<OpenAiChoice>,
) {
    val result: String
        get() = choices[0].message.content
}

data class OpenAiChoice(
    val message: OpenAiChatMessage,
)
