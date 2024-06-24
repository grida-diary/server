package io.wwan13.aiclient.chat

data class ChatCompletionRequest(
    val model: String? = DEFAULT_CHAT_COMPLETION_MODEL,
    val messages: List<ChatMessage>,
) {
    constructor(
        model: String,
        role: String,
        prompt: String,
    ) : this(
        model,
        listOf(ChatMessage(role, prompt)),
    )
}

data class ChatCompletionResponse(
    val choices: List<Choice>,
) {
    val result: String
        get() = choices[0].message.content
}

data class Choice(
    val message: ChatMessage,
)

data class ChatMessage(
    val role: String? = DEFAULT_CHAT_COMPLETION_ROLE,
    val content: String,
)
