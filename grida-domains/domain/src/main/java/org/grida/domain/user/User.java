package org.grida.domain.user;

import org.grida.domain.core.DefaultDateTime;

public record User(
        long id,
        UserAccount account,
        UserProfile profile,
        DefaultDateTime defaultDateTime
) {
}
