package org.grida.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        boolean needOnboarding
) {
}
