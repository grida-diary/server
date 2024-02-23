package org.grida.domain.user;

import org.grida.domain.core.DefaultDateTime;

public record ProfileImage(
        long id,
        String ownerEmail,
        ImageDetail imageDetail,
        DefaultDateTime dateTime
) {
}
