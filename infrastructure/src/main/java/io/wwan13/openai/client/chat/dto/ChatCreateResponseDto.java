package io.wwan13.openai.client.chat.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatCreateResponseDto {

    private List<Choice> choices;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Choice {
        private ChatMessageDto message;

        protected String getMessageContent() {
            return message.getContent();
        }
    }

    public String getResultMessage() {
        return choices.get(0).getMessageContent();
    }
}
