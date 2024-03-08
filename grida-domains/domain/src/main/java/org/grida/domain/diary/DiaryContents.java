package org.grida.domain.diary;

import java.time.LocalDate;

public record DiaryContents(
        LocalDate targetDate,
        String content
) {
}
