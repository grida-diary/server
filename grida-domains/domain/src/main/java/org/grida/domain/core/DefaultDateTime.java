package org.grida.domain.core;

import java.time.LocalDateTime;

public record DefaultDateTime(
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt
) {
}
