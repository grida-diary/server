package org.grida.client.chat;

import org.grida.client.chat.dto.ChatRequest;
import org.grida.client.chat.dto.ChatResponse;
import org.grida.config.ClientHeaderConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "OpenAiChatCompletion",
        url = "https://api.openai.com/v1/chat/completions",
        configuration = {ClientHeaderConfig.class}
)
public interface OpenAiChatClient {
    @PostMapping
    ChatResponse createChatCompletion(ChatRequest chatCreateRequestDto);
}
