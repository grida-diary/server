package io.wwan13.diary.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.diary.dto.DiaryIdResponseDto;
import io.wwan13.domain.diaryimage.service.DiaryImageService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SaveDiaryImageUseCase {

    private final DiaryImageService diaryImageService;

    public DiaryIdResponseDto execute(Long diaryId, String imageUrl) {
        diaryImageService.save(diaryId, imageUrl);
        return new DiaryIdResponseDto(diaryId);
    }
}
