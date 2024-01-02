package io.wwan13.imagegenerate.prompt.profileimagegenerate;

import io.wwan13.imagegenerate.exception.KeywordCannotBeEmptyException;
import io.wwan13.imagegenerate.prompt.Keywords;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ProfileImageGenerateKeywords 는")
class ProfileImageGenerateKeywordsTest {

    @Test
    void builder_패턴으로_생성_가능하다() {
        // given
        String gender = "gender";
        int age = 21;

        // when
        ProfileImageGenerateKeywords keywords = ProfileImageGenerateKeywords.builder()
                .gender(gender)
                .age(age)
                .build();

        // then
        assertThat(keywords).isInstanceOf(Keywords.class);
        assertThat(keywords).isExactlyInstanceOf(ProfileImageGenerateKeywords.class);
        assertThat(keywords.getRegexes()).contains("#GENDER", "#AGE");
        assertThat(keywords.getKeyword("#GENDER")).isEqualTo(gender);
        assertThat(keywords.getKeyword("#AGE")).isEqualTo(Integer.toString(age));

    }
    
    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {
    
        @Test
        void 키워드가_전부_채워지지_않은_경우() {
            // given
            String gender = "gender";
          
            // when, then
            assertThatThrownBy(
                    () -> ProfileImageGenerateKeywords.builder()
                            .gender(gender)
                            .build())
                    .isExactlyInstanceOf(KeywordCannotBeEmptyException.class);
        }
    }
}