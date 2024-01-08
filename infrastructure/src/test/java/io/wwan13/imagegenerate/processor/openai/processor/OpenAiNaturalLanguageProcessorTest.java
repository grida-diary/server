package io.wwan13.imagegenerate.processor.openai.processor;

import io.wwan13.imagegenerate.config.OpenAiProperties;
import io.wwan13.imagegenerate.exception.CannotParsingResponseException;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessResult;
import io.wwan13.imagegenerate.processor.openai.client.chat.OpenAiChatClient;
import io.wwan13.imagegenerate.processor.openai.client.chat.dto.ChatCreateRequestDto;
import io.wwan13.imagegenerate.processor.openai.client.chat.dto.ChatCreateResponseDto;
import io.wwan13.imagegenerate.processor.openai.client.chat.dto.ChatMessageDto;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("OpenAiNaturalLanguageProcessor 는")
class OpenAiNaturalLanguageProcessorTest {

    static class MockOpenAiProperties extends OpenAiProperties {

        public MockOpenAiProperties() {
            super("secretKey",
                    "imageModel",
                    "chatModel",
                    "chatRole");
        }
    }

    @Test
    void 자연어_처리_작업을_수행할_수_있다() {
        // given
        class MockChatClient implements OpenAiChatClient {
            @Override
            public ChatCreateResponseDto createChatCompletion(ChatCreateRequestDto chatCreateRequestDto) {
                ChatMessageDto messageDto =
                        new ChatMessageDto("role", "{\"emotion\":\"emotion\",\"behavior\":\"behavior\"}");
                ChatCreateResponseDto.Choice choice = new ChatCreateResponseDto.Choice(messageDto);
                return new ChatCreateResponseDto(List.of(choice));
            }
        }

        OpenAiNaturalLanguageProcessor naturalLanguageProcessor
                = new OpenAiNaturalLanguageProcessor(new MockOpenAiProperties(), new MockChatClient());

        // when
        NaturalLanguageProcessResult result = naturalLanguageProcessor.proceed("prompt");

        // then
        assertThat(result.getEmotion()).isEqualTo("emotion");
        assertThat(result.getBehavior()).isEqualTo("behavior");
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {

        @Test
        void GPT_응답이_정해진_JSON_형식이_아닌_경우() {
            // given
            class MockChatClient implements OpenAiChatClient {
                @Override
                public ChatCreateResponseDto createChatCompletion(ChatCreateRequestDto chatCreateRequestDto) {
                    ChatMessageDto messageDto =
                            new ChatMessageDto("role", "{\"emot\":\"emotion\",\"beh\":\"behavior\"}");
                    ChatCreateResponseDto.Choice choice = new ChatCreateResponseDto.Choice(messageDto);
                    return new ChatCreateResponseDto(List.of(choice));
                }
            }

            OpenAiNaturalLanguageProcessor naturalLanguageProcessor
                    = new OpenAiNaturalLanguageProcessor(new MockOpenAiProperties(), new MockChatClient());

            // when, then
            assertThatThrownBy(() -> naturalLanguageProcessor.proceed("prompt"))
                    .isExactlyInstanceOf(CannotParsingResponseException.class);
        }
    }
}