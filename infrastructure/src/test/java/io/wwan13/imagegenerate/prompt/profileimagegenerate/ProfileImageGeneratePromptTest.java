package io.wwan13.imagegenerate.prompt.profileimagegenerate;

import io.wwan13.imagegenerate.config.PromptProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ProfileImageGeneratePrompt 는")
class ProfileImageGeneratePromptTest {

    static class MockPromptProperties extends PromptProperties {
        public MockPromptProperties(String profileImageGeneratePrompt) {
            super("naturalLanguageProcess",
                    "diaryImageGenerate",
                    profileImageGeneratePrompt);
        }
    }

    @Test
    void 프로필_이미지를_생성하는_프롬프트를_생성할_수_있다() {
        // given
        String promptValue = "성별: #GENDER ,나이: #AGE";
        ProfileImageGeneratePrompt prompt = new ProfileImageGeneratePrompt(new MockPromptProperties(promptValue));

        ProfileImageGenerateKeywords keywords = ProfileImageGenerateKeywords.builder()
                .gender("gender")
                .age(21)
                .build();

        String expected = "성별: gender ,나이: 21";;

        // when
        String result = prompt.create(keywords);

        // then
        assertThat(result).isEqualTo(expected);
    }
}