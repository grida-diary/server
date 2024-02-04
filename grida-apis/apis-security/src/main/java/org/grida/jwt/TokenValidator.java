package org.grida.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.grida.exception.ApisSecurityException;
import org.springframework.stereotype.Component;

import java.security.Key;

import static org.grida.exception.ApisSecurityErrorCode.*;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    private final Key key;

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new ApisSecurityException(INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new ApisSecurityException(EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new ApisSecurityException(UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new ApisSecurityException(WRONG_TOKEN);
        }
    }
}