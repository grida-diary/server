package io.wwan13.imagegenerate.processor.openai.processor;

import io.wwan13.imagegenerate.config.OpenAiProperties;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessResult;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessor;
import io.wwan13.imagegenerate.processor.openai.client.chat.OpenAiChatClient;
import io.wwan13.imagegenerate.processor.openai.client.chat.dto.ChatCreateRequestDto;
import io.wwan13.imagegenerate.processor.openai.client.chat.dto.ChatCreateResponseDto;

public class OpenAiNaturalLanguageProcessor implements NaturalLanguageProcessor {

    private final OpenAiProperties properties;
    private final OpenAiChatClient chatClient;


    public OpenAiNaturalLanguageProcessor(OpenAiProperties properties, OpenAiChatClient chatClient) {
        this.properties = properties;
        this.chatClient = chatClient;
    }

    @Override
    public NaturalLanguageProcessResult proceed(String prompt) {
        ChatCreateRequestDto request = ChatCreateRequestDto.builder()
                .model(properties.getChatModel())
                .role(properties.getChatRole())
                .content(prompt)
                .build();

        ChatCreateResponseDto response = chatClient.createChatCompletion(request);
        return response.toProcessResult();
    }
}
