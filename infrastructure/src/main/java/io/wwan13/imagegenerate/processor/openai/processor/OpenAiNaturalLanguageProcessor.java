package io.wwan13.imagegenerate.processor.openai.processor;

import io.wwan13.imagegenerate.processor.openai.client.chat.OpenAiChatClient;
import io.wwan13.imagegenerate.processor.openai.client.chat.dto.ChatCreateRequestDto;
import io.wwan13.imagegenerate.processor.openai.client.chat.dto.ChatCreateResponseDto;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessResult;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenAiNaturalLanguageProcessor implements NaturalLanguageProcessor {

    private final OpenAiChatClient chatClient;
    private final String model;
    private final String role;


    public OpenAiNaturalLanguageProcessor(OpenAiChatClient chatClient,
                                          @Value("${open-ai.chat.model}") String model,
                                          @Value("${open-ai.chat.role}") String role) {
        this.chatClient = chatClient;
        this.model = model;
        this.role = role;
    }

    @Override
    public NaturalLanguageProcessResult proceed(String prompt) {
        ChatCreateRequestDto request = ChatCreateRequestDto.builder()
                .model(model)
                .role(role)
                .content(prompt)
                .build();

        ChatCreateResponseDto response = chatClient.createChatCompletion(request);
        return response.toProcessResult();
    }
}
