package org.grida.client.chat.dto;

public record ChatMessage(
        String role,
        String content
) {
}