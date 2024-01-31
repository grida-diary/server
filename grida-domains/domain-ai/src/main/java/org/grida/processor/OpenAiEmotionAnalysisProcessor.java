package org.grida.processor;

import lombok.RequiredArgsConstructor;
import org.grida.client.chat.OpenAiChatClient;
import org.grida.client.chat.dto.ChatRequestDto;
import org.grida.client.chat.dto.ChatResponseDto;
import org.grida.config.OpenAiProperties;
import org.grida.processor.emotionanalysis.EmotionAnalysisProcessor;
import org.grida.processor.emotionanalysis.EmotionAnalysisResult;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenAiEmotionAnalysisProcessor implements EmotionAnalysisProcessor {

    private final OpenAiProperties properties;
    private final OpenAiChatClient chatClient;

    @Override
    public EmotionAnalysisResult proceed(String prompt) {
        ChatRequestDto request = ChatRequestDto.of(
                properties.getChatModel(),
                properties.getChatRole(),
                prompt
        );

        ChatResponseDto response = chatClient.createChatCompletion(request);
        return response.toResult();
    }
}
