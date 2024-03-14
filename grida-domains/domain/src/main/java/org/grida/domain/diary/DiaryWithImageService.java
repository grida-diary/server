package org.grida.domain.diary;

import lombok.RequiredArgsConstructor;
import org.grida.datetime.DateTimePicker;
import org.grida.datetime.DateTimeRange;
import org.grida.domain.core.ImageDetail;
import org.grida.exception.DomainException;
import org.grida.transaction.TransactionHandler;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.grida.exception.DomainErrorCode.CANNOT_REFRESH_DIARY_IMAGE;

@Service
@RequiredArgsConstructor
public class DiaryWithImageService {

    private final DiaryRepository diaryRepository;
    private final DiaryImageRepository diaryImageRepository;
    private final DateTimePicker dateTimePicker;
    private final TransactionHandler transactionHandler;

    public List<DiaryImage> readTargetMothImages(String userEmail, int year, int month) {
        DateTimeRange targetRange = dateTimePicker.monthOfYearRange(year, month);
        List<Long> diaryIds = diaryRepository.findIdsByUserEmailAndTargetDateBetween(userEmail, targetRange);
        return diaryIds.stream()
                .map(diaryImageRepository::findActivateImageByDiaryId)
                .toList();
    }

    public DiaryWithImage readOne(long diaryImage) {
        Diary diary = diaryRepository.findById(diaryImage);
        DiaryImage image = diaryImageRepository.findActivateImageByDiaryId(diaryImage);

        return new DiaryWithImage(diary, image);
    }

    public List<DiaryWithImage> readAll(String userEmail, DiaryCursor cursor) {
        List<Diary> diaries = diaryRepository.findAllByUseEmailAndDiaryCursor(userEmail, cursor);
        return diaries.stream()
                .map(diary -> {
                    DiaryImage image = diaryImageRepository.findActivateImageByDiaryId(diary.id());
                    return new DiaryWithImage(diary, image);
                })
                .toList();
    }

    public long refreshImage(long diaryId, String imagePath) {
        validateCanRefresh(diaryId);

        transactionHandler.execute(() -> {
            diaryImageRepository.deactivateImage(diaryId);
            diaryImageRepository.save(diaryId, ImageDetail.activateImage(imagePath), dateTimePicker.now());
            diaryRepository.useImageRefreshChance(diaryId, dateTimePicker.now());
        });

        return diaryId;
    }

    public long modifyContentsAndRefreshImage(long diaryId, DiaryContents diaryContents, String imagePath) {
        validateCanRefresh(diaryId);

        return transactionHandler.execute(() -> {
            diaryImageRepository.deactivateImage(diaryId);
            diaryImageRepository.save(diaryId, ImageDetail.activateImage(imagePath), dateTimePicker.now());
            diaryRepository.useImageRefreshChance(diaryId, dateTimePicker.now());
            return diaryRepository.modifyContents(diaryId, diaryContents, dateTimePicker.now());
        });
    }

    private void validateCanRefresh(long diaryId) {
        if (diaryRepository.findImageRefreshChanceById(diaryId) == 0) {
            throw new DomainException(CANNOT_REFRESH_DIARY_IMAGE);
        }
    }
}
