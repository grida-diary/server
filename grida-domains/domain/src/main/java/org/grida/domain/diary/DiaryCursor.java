package org.grida.domain.diary;

import java.time.LocalDate;

public record DiaryCursor(
    LocalDate lastTargetDate,
    int size
) {
}
