package org.grida.dto.response;

public record CheckEmailResponse(
        String email,
        boolean isUsable
) {
}
