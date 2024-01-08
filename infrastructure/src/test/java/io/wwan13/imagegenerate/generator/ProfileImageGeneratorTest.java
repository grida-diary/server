package io.wwan13.imagegenerate.generator;

import io.wwan13.imagegenerate.config.PromptProperties;
import io.wwan13.imagegenerate.processor.image.ImageProcessResult;
import io.wwan13.imagegenerate.processor.image.ImageProcessor;
import io.wwan13.imagegenerate.prompt.profileimagegenerate.ProfileImageGeneratePrompt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ProfileImageGenerator 는")
class ProfileImageGeneratorTest {

    static class MockPromptProperties extends PromptProperties {
        public MockPromptProperties(String profileImageGeneratePrompt) {
            super("naturalLanguageProcess",
                    "diaryImageGenerate",
                    profileImageGeneratePrompt);
        }
    }

    static class MockImageProcessor implements ImageProcessor {

        @Override
        public ImageProcessResult proceed(String prompt, int n) {
            return new ImageProcessResult(List.of("imageUrl.com"));
        }
    }

    @Test
    void 프로필_이미지를_생성할_수_있다() {
        // given
        ProfileImageGeneratePrompt prompt
                = new ProfileImageGeneratePrompt(new MockPromptProperties("#GENDER #AGE"));
        ImageProcessor imageProcessor = new MockImageProcessor();
        ProfileImageGenerator generator = new ProfileImageGenerator(prompt, imageProcessor);

        // when
        String imageUrl = generator.generate("Man", 21);

        // then
        assertThat(imageUrl).isEqualTo("imageUrl.com");
    }
}