package org.grida.domain.diary;

import org.grida.domain.core.DefaultDateTime;
import org.grida.domain.core.ImageDetail;

public record DiaryImage(
        long id,
        long diaryId,
        ImageDetail imageDetail,
        DefaultDateTime dateTime
) {
}
