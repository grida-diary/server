package io.wwan13.imagegenerate.prompt.diaryimagegenerate;

import io.wwan13.imagegenerate.config.PromptProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("DiaryImageGeneratePrompt 는")
class DiaryImageGeneratePromptTest {

    static class MockPromptProperties extends PromptProperties {
        public MockPromptProperties(String diaryImageGeneratePrompt) {
            super("naturalLanguageProcess",
                    diaryImageGeneratePrompt,
                    "profileImage");
        }
    }

    @Test
    void 일기_이미지를_생성하는_프롬프트를_생성할_수_있다() {
        // given
        String promptValue = "성별: #GENDER ,나이: #AGE , 감정: #EMOTION , 행동: #BEHAVIOR";
        DiaryImageGeneratePrompt prompt = new DiaryImageGeneratePrompt(new MockPromptProperties(promptValue));

        DiaryImageGenerateKeywords keywords = DiaryImageGenerateKeywords.builder()
                .gender("gender")
                .age(21)
                .emotion("emotion")
                .behavior("behavior")
                .build();

        String expected = "성별: gender ,나이: 21 , 감정: emotion , 행동: behavior";

        // when
        String result = prompt.create(keywords);

        // then
        assertThat(result).isEqualTo(expected);
    }
}