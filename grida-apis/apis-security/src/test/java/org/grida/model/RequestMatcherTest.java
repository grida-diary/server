package org.grida.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("RequestMatcher 는")
class RequestMatcherTest {

    private RequestMatcher createElsePermitRequestMatcher() {
        return RequestMatcher.builder()
                .antMatcher(
                        "/api/admin/**",
                        "ROLE_ADMIN"
                )
                .antMatcher(
                        "/api/user/admin",
                        "ROLE_ADMIN"
                )
                .antMatcher(
                        "/api/user/**",
                        "ROLE_USER"
                )
                .elsePermit();
    }

    private RequestMatcher createElseAuthenticatedRequestMatcher() {
        return RequestMatcher.builder()
                .antMatcher(
                        "/api/member/**",
                        "ROLE_ADMIN", "ROLE_USER"
                )
                .elseAuthenticated();
    }

    @ParameterizedTest(name = "RequestUri = {0}, Role = {1}, Expected = {2}")
    @CsvSource(value = {
            "/api/admin/member, ROLE_ADMIN, true",
            "/api/admin/member, ROLE_USER, false",
            "/api/user/item, ROLE_USER, true",
            "/api/super/item, ROLE_USER, true"
    })
    void role_과_URI_검증이_가능하다(String requestUri, String role, boolean expected) {
        // given
        RequestMatcher requestMatcher = createElsePermitRequestMatcher();

        // when
        boolean result = requestMatcher.matches(requestUri, role);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "RequestUri = {0}, Role = {1}, Expected = {2}")
    @CsvSource(value = {
            "/api/user/admin, ROLE_USER, false",
            "/api/user/admin, ROLE_ADMIN, true",
            "/api/user/item, ROLE_ADMIN, false"
    })
    void 먼저_등록한_pattern_이_높은_우선순위를_가진다(String requestUri, String role, boolean expected) {
        // given
        RequestMatcher requestMatcher = createElsePermitRequestMatcher();

        // when
        boolean result = requestMatcher.matches(requestUri, role);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 등록되지_않은_pattern_의_모든_요청을_허락할_수_있다() {
        // given
        RequestMatcher requestMatcher = createElsePermitRequestMatcher();
        String requestUri = "/not/registered";
        String role = "ROLE_USER";

        // when
        boolean result = requestMatcher.matches(requestUri, role);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 등록되지_않은_pattern_의_모든_요청을_막을_수_있다() {
        // given
        RequestMatcher requestMatcher = createElseAuthenticatedRequestMatcher();
        String requestUri = "/not/registered";
        String role = "ROLE_USER";

        // when
        boolean result = requestMatcher.matches(requestUri, role);

        // then
        assertThat(result).isFalse();
    }
}