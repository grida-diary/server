package org.grida.domain.user;

public record UserProfile(
        String nickname,
        int age,
        Gender gender
) {
}
