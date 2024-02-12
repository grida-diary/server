package org.grida.authorizedrequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.grida.authorizedrequest.AuthorizedRequestBuilder.Support.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("RequestMatcher 는")
class AuthorizedRequestTest {

    private AuthorizedRequest createElsePermitRequestMatcher() {
        return AuthorizedRequestBuilder.withPatterns()
                .antMatchers(
                        httpMethods(GET, POST),
                        "/api/admin/**",
                        hasRoles("ROLE_ADMIN")
                )
                .antMatchers(
                        "/api/user/admin",
                        hasRoles("ROLE_ADMIN")
                )
                .antMatchers(
                        "/api/user/**",
                        hasRoles("ROLE_USER")
                )
                .antMatchers(
                        "/api/item/permit/**",
                        permitAll()
                )
                .antMatchers(
                        allHttpMethods(),
                        "/api/item/authenticated/**",
                        authenticated()
                )
                .elseRequestPermit();
    }

    private AuthorizedRequest createElseAuthenticatedRequestMatcher() {
        return AuthorizedRequestBuilder.withPatterns()
                .antMatchers(
                        "/api/member/**",
                        hasRoles("ROLE_ADMIN", "ROLE_USER")
                )
                .elseRequestAuthenticated();
    }

    @ParameterizedTest(name = "HttpMethod = {0}, RequestUri = {1}, Role = {2}, Expected = {3}")
    @CsvSource(value = {
            "GET, /api/admin/member, ROLE_ADMIN, true",
            "PATCH, /api/admin/member, ROLE_ADMIN, true",
            "GET, /api/admin/member, ROLE_USER, false",
            "GET, /api/user/item, ROLE_USER, true",
            "GET, /api/super/item, ROLE_USER, true",
            "GET, /api/item/authenticated/test, ROLE_ANONYMOUS, false",
            "POST, /api/item/permit/test, ROLE_ANONYMOUS, true",
    })
    void role_과_URI_검증이_가능하다(String method, String requestUri, String role, boolean expected) {
        // given
        AuthorizedRequest authorizedRequest = createElsePermitRequestMatcher();

        // when
        boolean result = authorizedRequest.matches(method, requestUri, role);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "RequestUri = {0}, Role = {1}, Expected = {2}")
    @CsvSource(value = {
            "GET, /api/user/admin, ROLE_USER, false",
            "GET, /api/user/admin, ROLE_ADMIN, true",
            "GET, /api/user/item, ROLE_ADMIN, false"
    })
    void 먼저_등록한_pattern_이_높은_우선순위를_가진다(String method, String requestUri, String role, boolean expected) {
        // given
        AuthorizedRequest authorizedRequest = createElsePermitRequestMatcher();

        // when
        boolean result = authorizedRequest.matches("POST", requestUri, role);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 등록되지_않은_pattern_의_모든_요청을_허락할_수_있다() {
        // given
        AuthorizedRequest authorizedRequest = createElsePermitRequestMatcher();
        String requestUri = "/not/registered";
        String role = "ROLE_USER";

        // when
        boolean result = authorizedRequest.matches("POST", requestUri, role);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 등록되지_않은_pattern_의_모든_요청을_막을_수_있다() {
        // given
        AuthorizedRequest authorizedRequest = createElseAuthenticatedRequestMatcher();
        String requestUri = "/not/registered";
        String role = "ROLE_USER";

        // when
        boolean result = authorizedRequest.matches("POST", requestUri, role);

        // then
        assertThat(result).isFalse();
    }
}