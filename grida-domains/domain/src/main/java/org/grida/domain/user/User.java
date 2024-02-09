package org.grida.domain.user;

import org.grida.domain.core.DefaultDateTime;

public record User(
        long id,
        UserRole role,
        UserAccount account,
        UserProfile profile,
        DefaultDateTime defaultDateTime
) {
}
