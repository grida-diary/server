package io.wwan13.imagegenerate.processor.openai.client.chat.dto;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatCreateRequestDto {

    private String model;
    private List<ChatMessageDto> messages;

    @Builder
    public ChatCreateRequestDto(String model, String role, String content) {
        this.model = model;
        ChatMessageDto message = new ChatMessageDto(role, content);
        this.messages = List.of(message);
    }
}
