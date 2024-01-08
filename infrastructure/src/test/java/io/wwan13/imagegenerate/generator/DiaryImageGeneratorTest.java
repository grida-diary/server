package io.wwan13.imagegenerate.generator;

import io.wwan13.imagegenerate.config.PromptProperties;
import io.wwan13.imagegenerate.processor.image.ImageProcessResult;
import io.wwan13.imagegenerate.processor.image.ImageProcessor;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessResult;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessor;
import io.wwan13.imagegenerate.prompt.diaryimagegenerate.DiaryImageGeneratePrompt;
import io.wwan13.imagegenerate.prompt.naturallanguageprocess.NaturalLanguageProcessPrompt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("DiaryImageGenerator 는")
class DiaryImageGeneratorTest {

    static class MockPromptProperties extends PromptProperties {
        public MockPromptProperties(String naturalLanguageProcessPrompt, String profileImageGeneratePrompt) {
            super(naturalLanguageProcessPrompt,
                    profileImageGeneratePrompt,
                    "profileImage");
        }
    }

    static class MockNaturalLanguageProcessor implements NaturalLanguageProcessor {

        @Override
        public NaturalLanguageProcessResult proceed(String prompt) {
            return new NaturalLanguageProcessResult("emotion", "behavior");
        }
    }

    static class MockImageProcessor implements ImageProcessor {

        @Override
        public ImageProcessResult proceed(String prompt, int n) {
            return new ImageProcessResult(List.of("imageUrl.com"));
        }
    }

    @Test
    void 일기_이미지를_생성할_수_있다() {
        // given
        PromptProperties promptProperties = new MockPromptProperties("#DIARY",
                "#EMOTION #BEHAVIOR #GENDER #AGE");
        NaturalLanguageProcessPrompt naturalLanguageProcessPrompt = new NaturalLanguageProcessPrompt(promptProperties);
        DiaryImageGeneratePrompt diaryImageGeneratePrompt
                = new DiaryImageGeneratePrompt(promptProperties);

        NaturalLanguageProcessor naturalLanguageProcessor = new MockNaturalLanguageProcessor();
        ImageProcessor imageProcessor = new MockImageProcessor();

        DiaryImageGenerator generator = new DiaryImageGenerator(
                naturalLanguageProcessPrompt,
                diaryImageGeneratePrompt,
                naturalLanguageProcessor,
                imageProcessor
        );

        // when
        String imageUrl = generator.generate("diary", "man", 21);

        // then
        assertThat(imageUrl).isEqualTo("imageUrl.com");
    }
}