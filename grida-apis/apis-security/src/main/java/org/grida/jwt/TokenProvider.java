package org.grida.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.grida.config.JwtProperties;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final String CLAIM_NAME_TYPE = "type";
    private static final String CLAIM_NAME_ROLE = "role";

    private final JwtProperties jwtProperties;
    private final Key key;

    public String createToken(TokenType type, TokenClaims claims) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(Long.toString(claims.userId()))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + type.getValidityInSeconds(jwtProperties)))
                .claim(CLAIM_NAME_TYPE, type.name())
                .claim(CLAIM_NAME_ROLE, claims.role())
                .signWith(key)
                .compact();
    }

    public TokenClaims decodeToken(String token) {
        Claims claims = getClaim(token);
        return new TokenClaims(
                Long.parseLong(claims.getSubject()),
                String.valueOf(claims.get(CLAIM_NAME_ROLE))
        );
    }

    private Claims getClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
