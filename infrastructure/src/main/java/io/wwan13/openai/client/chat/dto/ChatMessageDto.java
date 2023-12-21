package io.wwan13.openai.client.chat.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageDto {

    private String role;
    private String content;

    public ChatMessageDto(String content) {
        this.role = "system";
        this.content = content;
    }
}
