package io.wwan13.openai.chat.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatCreateResponseDto {

    private String id;
    private List<Choice> choices;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Choice {
        private Integer index;
        private ChatMessageDto message;
    }
}