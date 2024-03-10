package org.grida.domain.diary;

import org.grida.domain.core.ImageDetail;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryImageRepository {
    long save(long diaryId, ImageDetail imageDetail, LocalDateTime lastActionAt);
    DiaryImage findActivateImageByDiaryId(long diaryId);
    List<DiaryImage> findAllByDiaryId(long diaryId);
    void deactivateImage(long diaryId);
    void deleteAllByDiaryId(long diaryId);
}
