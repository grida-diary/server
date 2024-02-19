package org.grida.prompt.base;

import org.grida.base.KeywordBuilder;
import org.grida.base.Keywords;
import org.grida.exception.DomainImageException;
import org.junit.jupiter.api.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Keywords 는")
class KeywordsTest {

    static class StubKeywordBuilder extends KeywordBuilder {

        static StubKeywordBuilder with() {
            return new StubKeywordBuilder();
        }

        StubKeywordBuilder firstFiled(String first) {
            addKeyword("#FIRST#", first);
            return this;
        }

        StubKeywordBuilder secondFiled(String second) {
            addKeyword("#SECOND#", second);
            return this;
        }

        StubKeywordBuilder thirdFiled(String third) {
            addKeyword("#THIRD#", third);
            return this;
        }

        @Override
        protected Set<String> allKeys() {
            return Set.of("#FIRST#", "#SECOND#", "#THIRD#");
        }
    }

    @Test
    void 키워드를_생성할_수_있다() {
        // given
        String firstValue = "first";
        String secondValue = "second";
        String thirdValue = "third";

        // when
        Keywords keywords = StubKeywordBuilder.with()
                .firstFiled(firstValue)
                .secondFiled(secondValue)
                .thirdFiled(thirdValue)
                .build();

        // then
        assertThat(keywords).isInstanceOf(Keywords.class);
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {

        @Test
        void 모든_필드가_채워지지_않은_경우() {
            // given
            String firstValue = "first";
            String secondValue = "second";

            // when, then
            assertThatThrownBy(() ->
                    StubKeywordBuilder.with()
                            .firstFiled(firstValue)
                            .secondFiled(secondValue)
                            .build()
            ).isInstanceOf(DomainImageException.class);
        }
    }
}