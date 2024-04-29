package org.grida.processor;

import lombok.RequiredArgsConstructor;
import org.grida.client.chat.OpenAiChatClient;
import org.grida.client.chat.dto.ChatRequest;
import org.grida.client.chat.dto.ChatResponse;
import org.grida.config.OpenAiProperties;
import org.grida.processor.diaryanalyze.DiaryAnalyzeProcessor;
import org.grida.processor.diaryanalyze.DiaryAnalyzeResult;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenAiDiaryAnalyzeProcessor implements DiaryAnalyzeProcessor {

    private final OpenAiProperties properties;
    private final OpenAiChatClient chatClient;

    @Override
    public DiaryAnalyzeResult proceed(String prompt) {
        ChatRequest request = ChatRequest.of(
                properties.getChatModel(),
                properties.getChatRole(),
                prompt
        );

        ChatResponse response = chatClient.createChatCompletion(request);
        return response.toResult();
    }
}
