package io.wwan13.imagegenerate.prompt.naturallanguageprocess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("NaturalLanguageProcessPrompt 는")
class NaturalLanguageProcessPromptTest {

    @Test
    void 자연어_처리_작업을_수행하는_프롬프트를_생성할_수_있다() {
        // given
        String promptValue = "일기: #DIARY";
        NaturalLanguageProcessPrompt prompt = new NaturalLanguageProcessPrompt(promptValue);

        NaturalLanguageProcessKeywords keywords = NaturalLanguageProcessKeywords.builder()
                .diary("diary")
                .build();

        String expected = "일기: diary";
      
        // when
        String result = prompt.create(keywords);
      
        // then
        assertThat(result).isEqualTo(expected);
    }
}