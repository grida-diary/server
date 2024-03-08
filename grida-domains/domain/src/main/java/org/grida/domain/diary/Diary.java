package org.grida.domain.diary;

import org.grida.domain.core.DefaultDateTime;

public record Diary(
        long id,
        String ownerEmail,
        DiaryContents contents,
        int imageRefreshChance,
        DefaultDateTime dateTime
) {
}
