package io.wwan13.imagegenerate.processor.openai.client.chat;

import io.wwan13.imagegenerate.processor.openai.client.chat.dto.ChatCreateRequestDto;
import io.wwan13.imagegenerate.processor.openai.client.chat.dto.ChatCreateResponseDto;
import io.wwan13.imagegenerate.processor.openai.config.OpenAiHeaderConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "OpenAiChatCompletion",
        url = "https://api.openai.com/v1/chat/completions",
        configuration = {OpenAiHeaderConfig.class})
public interface OpenAiChatClient {
    @PostMapping
    ChatCreateResponseDto createChatCompletion(ChatCreateRequestDto chatCreateRequestDto);
}
