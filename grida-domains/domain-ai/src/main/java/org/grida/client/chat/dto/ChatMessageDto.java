package org.grida.client.chat.dto;

public record ChatMessageDto(
        String role,
        String content
) {
}