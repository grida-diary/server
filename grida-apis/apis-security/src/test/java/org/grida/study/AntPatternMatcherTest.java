package org.grida.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("AntPatternMatcher 는")
public class AntPatternMatcherTest {

    @Test
    void 패턴과_일치하면_참을_반환한다() {
        // given
        String pattern = "/api/member/**";
        String path = "/api/member/1";

        AntPathMatcher antPathMatcher = new AntPathMatcher();

        // when
        boolean result = antPathMatcher.match(pattern, path);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 패턴과_일치하지_않으면_거짓을_반환한다() {
        // given
        String pattern = "/api/member";
        String path = "/api/item/1";

        AntPathMatcher antPathMatcher = new AntPathMatcher();

        // when
        boolean result = antPathMatcher.match(pattern, path);

        // then
        assertThat(result).isFalse();
    }
}
