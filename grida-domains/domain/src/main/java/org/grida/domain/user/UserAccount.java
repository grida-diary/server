package org.grida.domain.user;

public record UserAccount(
        UserRole role,
        String email,
        String password
) {
}
