package org.grida.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@RequiredArgsConstructor
public class TokenDecoder {

    private static final String CLAIM_NAME_ROLE = "role";

    private final Key key;

    public TokenClaims decode(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new TokenClaims(
                Long.parseLong(claims.getSubject()),
                String.valueOf(claims.get(CLAIM_NAME_ROLE))
        );
    }
}
