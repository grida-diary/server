package io.wwan13.imagegenerate.prompt.naturallanguageprocess;

import io.wwan13.imagegenerate.exception.KeywordCannotBeEmptyException;
import io.wwan13.imagegenerate.prompt.Keywords;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("NaturalLanguageProcessKeywords 는")
class NaturalLanguageProcessKeywordsTest {

    @Test
    void builder_패턴으로_생성_가능하다() {
        // given
        String diary = "diary";

        // when
        NaturalLanguageProcessKeywords keywords = NaturalLanguageProcessKeywords.builder()
                .diary(diary)
                .build();

        // then
        assertThat(keywords).isInstanceOf(Keywords.class);
        assertThat(keywords).isExactlyInstanceOf(NaturalLanguageProcessKeywords.class);
        assertThat(keywords.getRegexes()).contains("#DIARY");
        assertThat(keywords.getKeyword("#DIARY")).isEqualTo(diary);
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {

        @Test
        void 키워드가_전부_채워지지_않은_경우() {
            // given, when, then
            assertThatThrownBy(() -> NaturalLanguageProcessKeywords.builder().build())
                    .isExactlyInstanceOf(KeywordCannotBeEmptyException.class);
        }
    }
}