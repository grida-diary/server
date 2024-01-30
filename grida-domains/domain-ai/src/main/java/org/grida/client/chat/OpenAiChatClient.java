package org.grida.client.chat;

import org.grida.client.chat.dto.ChatRequestDto;
import org.grida.client.chat.dto.ChatResponseDto;
import org.grida.config.ClientHeaderConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "OpenAiChatCompletion",
        url = "https://api.openai.com/v1/chat/completions",
        configuration = {ClientHeaderConfig.class})
public interface OpenAiChatClient {
    @PostMapping
    ChatResponseDto createChatCompletion(ChatRequestDto chatCreateRequestDto);
}
