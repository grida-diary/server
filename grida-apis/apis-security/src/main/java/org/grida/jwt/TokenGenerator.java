package org.grida.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.grida.config.JwtProperties;
import org.grida.datetime.DateTimePicker;
import org.grida.datetime.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TokenGenerator {

    private static final String CLAIM_NAME_TYPE = "type";
    private static final String CLAIM_NAME_ROLE = "role";

    private final JwtProperties jwtProperties;
    private final Key key;
    private final DateTimePicker dateTimePicker;

    public String createToken(TokenType type, TokenClaims claims) {
        LocalDateTime now = dateTimePicker.now();
        LocalDateTime expiration = now.plusSeconds(type.getValidityInSeconds(jwtProperties));

        return Jwts.builder()
                .setSubject(Long.toString(claims.userId()))
                .setIssuedAt(DateTimeUtil.toDate(now))
                .setExpiration(DateTimeUtil.toDate(expiration))
                .claim(CLAIM_NAME_TYPE, type.name())
                .claim(CLAIM_NAME_ROLE, claims.role())
                .signWith(key)
                .compact();
    }
}
