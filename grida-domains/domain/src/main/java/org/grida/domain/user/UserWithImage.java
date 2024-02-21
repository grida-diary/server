package org.grida.domain.user;

public record UserWithImage(
        User user,
        ProfileImage profileImage
) {
}
