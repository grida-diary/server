package org.grida.domain.diary;

import org.grida.datetime.DateTimeRange;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository {
    long save(String userEmail, DiaryContents contents, int imageRefreshChance, LocalDateTime lastActionAt);
    Diary findById(long id);
    List<Diary> findAllByUserEmailAndTargetDateBetween(String userEmail, DateTimeRange range);
    List<Diary> findAllByUseEmailAndDiaryCursor(String userEmail, DiaryCursor cursor);
    void delete(long id);
}
