package org.grida.domain.user;

import org.grida.domain.core.DefaultDateTime;
import org.grida.domain.core.ImageDetail;

public record ProfileImage(
        long id,
        String ownerEmail,
        ImageDetail imageDetail,
        DefaultDateTime dateTime
) {
}
