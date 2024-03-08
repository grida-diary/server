package org.grida.entity.diaryimage;

import org.grida.domain.core.ImageDetail;
import org.grida.domain.diary.DiaryImage;
import org.grida.entity.base.BaseMapper;

import java.time.LocalDateTime;

public class DiaryImageMapper {

    private DiaryImageMapper() {
    }

    public static DiaryImage toDiaryImage(DiaryImageEntity entity) {
        return new DiaryImage(
                entity.getId(),
                entity.getDiaryId(),
                new ImageDetail(entity.getImagePath(), entity.getIsActivate()),
                BaseMapper.toDefaultDateTime(entity)
        );
    }

    public static DiaryImageEntity toDiaryImageEntity(
            long diaryId,
            ImageDetail imageDetail,
            LocalDateTime lastActionAt
    ) {
        return DiaryImageEntity.builder()
                .diaryId(diaryId)
                .imagePath(imageDetail.imagePath())
                .isActivate(imageDetail.isActivate())
                .createdAt(lastActionAt)
                .lastModifiedAt(lastActionAt)
                .build();
    }
}
