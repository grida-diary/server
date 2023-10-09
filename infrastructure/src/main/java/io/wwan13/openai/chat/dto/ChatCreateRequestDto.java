package io.wwan13.openai.chat.dto;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatCreateRequestDto {

    private final String model = "gpt-3.5-turbo";
    private List<ChatMessageDto> messages;

    private ChatCreateRequestDto(String content) {
        ChatMessageDto message = new ChatMessageDto(content);
        this.messages = Arrays.asList(message);
    }

    public static ChatCreateRequestDto content(String content) {
        return new ChatCreateRequestDto(content);
    }
}
