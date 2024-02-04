package org.grida.jwt;

import io.jsonwebtoken.security.Keys;
import org.grida.config.JwtProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.security.Key;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("TokenCreator 는")
class TokenCreatorTest {

    private Key key() {
        return Keys.hmacShaKeyFor("secretkeysecretkeysecretkeysecretkey".getBytes());
    }

    private JwtProperties jwtProperties() {
        return new JwtProperties(
                "secretkey",
                126000,
                1260000);
    }

    @Test
    void jwt_토큰을_생성할_수_있다() {
        // given
        TokenCreator tokenCreator = new TokenCreator(jwtProperties(), key());
        TokenType tokenType = TokenType.ACCESS_TOKEN;
        TokenClaims tokenClaims = new TokenClaims(1L, "ROLE_USER");

        // when
        String jwtToken = tokenCreator.createToken(tokenType, tokenClaims);

        // then
        assertThat(jwtToken.length()).isGreaterThan(1);
    }
}