package org.grida.client.chat.dto;

import java.util.List;

public record ChatRequestDto(
        String model,
        List<ChatMessageDto> messages
) {

    public static ChatRequestDto of(String model, String role, String content) {
        ChatMessageDto message = new ChatMessageDto(role, content);
        return new ChatRequestDto(model, List.of(message));
    }
}
