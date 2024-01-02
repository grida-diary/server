package io.wwan13.imagegenerate.prompt.diaryimagegenerate;

import io.wwan13.imagegenerate.exception.KeywordCannotBeEmptyException;
import io.wwan13.imagegenerate.prompt.Keywords;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("DiaryImageGenerateKeywords 는")
class DiaryImageGenerateKeywordsTest {

    @Test
    void builder_패턴으로_생성_가능하다() {
        // given
        String gender = "Men";
        int age = 21;
        String emotion = "emotion";
        String behavior = "behavior";
      
        // when
        DiaryImageGenerateKeywords keywords = DiaryImageGenerateKeywords.builder()
                .gender(gender)
                .age(age)
                .emotion(emotion)
                .behavior(behavior)
                .build();
      
        // then
        assertThat(keywords).isInstanceOf(Keywords.class);
        assertThat(keywords).isExactlyInstanceOf(DiaryImageGenerateKeywords.class);
        assertThat(keywords.getRegexes()).contains("#GENDER", "#AGE", "#EMOTION", "#BEHAVIOR");
        assertThat(keywords.getKeyword("#GENDER")).isEqualTo(gender);
        assertThat(keywords.getKeyword("#AGE")).isEqualTo(Integer.toString(age));
        assertThat(keywords.getKeyword("#EMOTION")).isEqualTo(emotion);
        assertThat(keywords.getKeyword("#BEHAVIOR")).isEqualTo(behavior);
    }
    
    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {
    
        @Test
        void 키워드가_전부_채워지지_않은_경우() {
            // given
            String gender = "Men";
            String emotion = "emotion";
            String behavior = "behavior";
          
            // when, then
            assertThatThrownBy(
                    () -> DiaryImageGenerateKeywords.builder()
                            .gender(gender)
                            .emotion(emotion)
                            .behavior(behavior)
                            .build())
                    .isExactlyInstanceOf(KeywordCannotBeEmptyException.class);
        }
    }
}