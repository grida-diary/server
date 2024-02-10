package org.grida.jwt;

public record TokenClaims(
        String userEmail,
        String role
) {
}
