package org.grida.client.chat.dto;

import java.util.List;

public record ChatRequest(
        String model,
        List<ChatMessage> messages
) {

    public static ChatRequest of(String model, String role, String content) {
        ChatMessage message = new ChatMessage(role, content);
        return new ChatRequest(model, List.of(message));
    }
}
