package io.wwan13.imagegenerate.prompt;

import io.wwan13.imagegenerate.exception.InvalidPrefixRegexException;
import io.wwan13.imagegenerate.exception.InvalidSizeRegexException;
import io.wwan13.imagegenerate.exception.RegexCannotBeLowerCaseException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Keywords 는")
class KeywordsTest {

    class MockKeywords extends Keywords {

        public MockKeywords(Map<String, String> keywords) {
            super(keywords);
        }
    }

    @Test
    void 키워드를_생성할_수_있다() {
        // given
        Map<String, String> regexAndKeywords = Map.of("#REGEX", "keyword");

        // when
        Keywords keywords = new MockKeywords(regexAndKeywords);

        // then
        assertThat(keywords).isInstanceOf(Keywords.class);
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {

        @ParameterizedTest(name = "prefix가 {0}인 경우")
        @ValueSource(strings = {"!", "@", "$", "%", "^", "&", "*"})
        void regex_의_prefix_가_잘못_된_경우(String prefix) {
            // given
            Map<String, String> regexAndKeywords = Map.of(prefix + "REGEX", "keyword");

            // when, then
            assertThatThrownBy(() -> new MockKeywords(regexAndKeywords))
                    .isExactlyInstanceOf(InvalidPrefixRegexException.class);
        }

        @ParameterizedTest(name = "regex가 {0}인 경우")
        @ValueSource(strings = {"#REgEX", "#REGEx", "#REgex", "#reGex", "#regex"})
        void regex_에_소문자가_포함되어_있는_경우(String regex) {
            // given
            Map<String, String> regexAndKeywords = Map.of(regex, "keyword");

            // when, then
            assertThatThrownBy(() -> new MockKeywords(regexAndKeywords))
                    .isExactlyInstanceOf(RegexCannotBeLowerCaseException.class);
        }

        @Test
        void regex_의_크기가_2_미만인_경우() {
            // given
            String regex = "#";
            Map<String, String> regexAndKeywords = Map.of(regex, "keyword");

            // when, then
            assertThatThrownBy(() -> new MockKeywords(regexAndKeywords))
                    .isExactlyInstanceOf(InvalidSizeRegexException.class);
        }
    }
}