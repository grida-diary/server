package org.grida.entity.diary;

import org.grida.domain.diary.Diary;
import org.grida.domain.diary.DiaryContents;
import org.grida.entity.base.BaseMapper;

import java.time.LocalDateTime;

public class DiaryMapper {

    private DiaryMapper() {
    }

    public static Diary toDiary(DiaryEntity entity) {
        return new Diary(
                entity.getId(),
                entity.getUserEmail(),
                new DiaryContents(entity.getTargetDate(), entity.getContent()),
                entity.getImageRefreshChance(),
                BaseMapper.toDefaultDateTime(entity)
        );
    }

    public static DiaryEntity toDiaryEntity(
            String userEmail,
            DiaryContents diaryContents,
            int refreshChance,
            LocalDateTime lastActionAt
    ) {
        return DiaryEntity.builder()
                .userEmail(userEmail)
                .targetDate(diaryContents.targetDate())
                .content(diaryContents.content())
                .imageRefreshChance(refreshChance)
                .createdAt(lastActionAt)
                .lastModifiedAt(lastActionAt)
                .build();
    }
}
