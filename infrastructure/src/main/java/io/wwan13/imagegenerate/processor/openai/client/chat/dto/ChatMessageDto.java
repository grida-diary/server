package io.wwan13.imagegenerate.processor.openai.client.chat.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatMessageDto {

    private String role;
    private String content;
}
