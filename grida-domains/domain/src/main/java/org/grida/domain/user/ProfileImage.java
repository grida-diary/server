package org.grida.domain.user;

import org.grida.domain.core.DefaultDateTime;

public record ProfileImage(
        long id,
        ImageDetail imageDetail,
        DefaultDateTime dateTime
) {
}
