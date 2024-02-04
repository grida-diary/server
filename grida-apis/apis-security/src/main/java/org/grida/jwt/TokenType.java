package org.grida.jwt;

import lombok.RequiredArgsConstructor;
import org.grida.config.JwtProperties;

import java.util.function.Function;

@RequiredArgsConstructor
public enum TokenType {

    ACCESS_TOKEN(JwtProperties::getAccessTokenValidityInSecond),
    REFRESH_TOKEN(JwtProperties::getRefreshTokenValidityInSecond);

    private final Function<JwtProperties, Long> validityInSeconds;

    public Long getValidityInSeconds(JwtProperties properties) {
        return validityInSeconds.apply(properties);
    }
}
