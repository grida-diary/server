package org.grida.client.chat.dto;

import org.grida.exception.DomainAiException;
import org.grida.processor.emotionanalysis.EmotionAnalysisResult;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ChatResponseDto 는")
class ChatResponseDtoTest {

    @Test
    void 반환_값을_EmotionAnalysisResult_로_mapping_할_수_있다() {
        // given
        ChatMessageDto message = new ChatMessageDto("",
                "{\"emotion\":\"emotion\",\"behavior\":\"behavior\"}");
        List<ChatResponseDto.Choice> choices = List.of(new ChatResponseDto.Choice(message));

        ChatResponseDto responseDto = new ChatResponseDto(choices);

        // when
        EmotionAnalysisResult result = responseDto.toResult();

        // then
        assertThat(result.emotion()).isEqualTo("emotion");
        assertThat(result.behavior()).isEqualTo("behavior");
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {

        @Test
        void json_형식이_올바르지_않은_경우() {
            // given
            ChatMessageDto message = new ChatMessageDto("",
                    "emotion\":\"emotion\"behavior\":\"behavior");
            List<ChatResponseDto.Choice> choices = List.of(new ChatResponseDto.Choice(message));

            ChatResponseDto responseDto = new ChatResponseDto(choices);

            // when, then
            assertThatThrownBy(() -> responseDto.toResult())
                    .isInstanceOf(DomainAiException.class);
        }

        @Test
        void 필드_값이_일치하지_않는_경우() {
            // given
            ChatMessageDto message = new ChatMessageDto("",
                    "{\"emo\":\"emotion\",\"beh\":\"behavior\"}");
            List<ChatResponseDto.Choice> choices = List.of(new ChatResponseDto.Choice(message));

            ChatResponseDto responseDto = new ChatResponseDto(choices);

            // when, then
            assertThatThrownBy(() -> responseDto.toResult())
                    .isInstanceOf(DomainAiException.class);
        }
    }
}