package org.grida.datetime;

import java.time.LocalDateTime;

public record DateTimeRange(
        LocalDateTime start,
        LocalDateTime end
) {
}
