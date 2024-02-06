package org.grida.jwt;

import io.jsonwebtoken.security.Keys;
import org.grida.config.JwtProperties;
import org.grida.datetime.DateTimePicker;
import org.grida.exception.ApisSecurityErrorCode;
import org.grida.exception.ApisSecurityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("TokenValidator 는")
class TokenValidatorTest {

    private Key key() {
        return Keys.hmacShaKeyFor("secretkeysecretkeysecretkeysecretkey".getBytes());
    }

    private JwtProperties jwtProperties() {
        return new JwtProperties("secretkey", -1, -1);
    }

    static class StubDateTimePicker extends DateTimePicker {
        @Override
        public LocalDateTime now() {
            return LocalDateTime.of(2024, 1, 1, 12, 0, 0, 0);
        }
    }

    private TokenGenerator tokenGenerator() {
        return new TokenGenerator(jwtProperties(), key(), new StubDateTimePicker());
    }

    @Test
    void 토큰이_잘못_되었다면_예외가_발생한다() {
        // given
        TokenValidator tokenValidator = new TokenValidator(key());
        String token = "wrongToken";

        // when, then
        assertThatThrownBy(() -> tokenValidator.validateToken(token))
                .isInstanceOf(ApisSecurityException.class)
                .hasFieldOrPropertyWithValue("errorCode", ApisSecurityErrorCode.INVALID_TOKEN);
    }

    @Test
    void 토큰이_만료_되었다면_예외가_발생한다() {
        // given
        TokenValidator tokenValidator = new TokenValidator(key());
        String token = tokenGenerator().createToken(TokenType.ACCESS_TOKEN, new TokenClaims(1l, "ROLE_USER"));

        // when, then
        assertThatThrownBy(() -> tokenValidator.validateToken(token))
                .isInstanceOf(ApisSecurityException.class)
                .hasFieldOrPropertyWithValue("errorCode", ApisSecurityErrorCode.EXPIRED_TOKEN);
    }
}