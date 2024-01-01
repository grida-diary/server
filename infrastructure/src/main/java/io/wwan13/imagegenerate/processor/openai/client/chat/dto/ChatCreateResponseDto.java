package io.wwan13.imagegenerate.processor.openai.client.chat.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.wwan13.imagegenerate.exception.CannotParsingResponseException;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatCreateResponseDto {

    private List<Choice> choices;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Choice {
        private ChatMessageDto message;

        private String getMessageContent() {
            return message.getContent();
        }
    }

    public NaturalLanguageProcessResult toProcessResult() {
        String chatResult = choices.get(0).getMessageContent();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(chatResult, NaturalLanguageProcessResult.class);
        } catch (JsonProcessingException e) {
            throw new CannotParsingResponseException();
        }
    }
}
