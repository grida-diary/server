package org.grida.jwt;

public record TokenClaims(
        long userId,
        String role
) {
}
