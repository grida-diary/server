package io.wwan13.openai.processor;

import io.wwan13.annotation.Processor;
import io.wwan13.openai.chat.OpenAiChatClient;
import io.wwan13.openai.chat.dto.ChatCreateRequestDto;
import io.wwan13.openai.chat.dto.ChatCreateResponseDto;
import io.wwan13.openai.model.ProcessResult;
import io.wwan13.openai.util.ChatCompletionUtil;
import lombok.RequiredArgsConstructor;

@Processor
@RequiredArgsConstructor
public class ChatCompletionProcessor {

    private final OpenAiChatClient chatClient;
    private final ChatCompletionUtil chatCompletionUtil;

    public ProcessResult proceed(String content) {
        String prompt = chatCompletionUtil.createPrompt(content);
        ChatCreateRequestDto request = ChatCreateRequestDto.prompt(prompt);

        ChatCreateResponseDto response = chatClient.createChatCompletion(request);
        return chatCompletionUtil.getResult(response);
    }
}
