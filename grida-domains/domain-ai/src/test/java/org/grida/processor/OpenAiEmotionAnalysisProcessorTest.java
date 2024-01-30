package org.grida.processor;

import org.grida.client.chat.OpenAiChatClient;
import org.grida.client.chat.dto.ChatMessageDto;
import org.grida.client.chat.dto.ChatRequestDto;
import org.grida.client.chat.dto.ChatResponseDto;
import org.grida.config.OpenAiProperties;
import org.grida.processor.emotionanalysis.EmotionAnalysisResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("OpenAiEmotionAnalysisProcessor 는")
@ExtendWith(MockitoExtension.class)
class OpenAiEmotionAnalysisProcessorTest {

    static class StubProperties extends OpenAiProperties {
        public StubProperties() {
            super("", "", "", "");
        }
    }

    static class StubChatClient implements OpenAiChatClient {
        ChatMessageDto message = new ChatMessageDto("",
                "{\"emotion\":\"emotion\",\"behavior\":\"behavior\"}");
        List<ChatResponseDto.Choice> choices = List.of(new ChatResponseDto.Choice(message));

        @Override
        public ChatResponseDto createChatCompletion(ChatRequestDto chatCreateRequestDto) {
            return new ChatResponseDto(choices);
        }
    }

    @Test
    void 감정과_행동을_분석한다() {
        // given
        OpenAiEmotionAnalysisProcessor emotionAnalysisProcessor = new OpenAiEmotionAnalysisProcessor(
                new StubProperties(),
                new StubChatClient()
        );

        // when
        EmotionAnalysisResult result = emotionAnalysisProcessor.proceed("");

        // then
        assertThat(result.emotion()).isEqualTo("emotion");
        assertThat(result.behavior()).isEqualTo("behavior");
    }
}