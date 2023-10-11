package io.wwan13.domain.diaryimage.service;

import io.wwan13.domain.diary.entity.Diary;
import io.wwan13.domain.diary.exception.DiaryNotFoundException;
import io.wwan13.domain.diary.repository.DiaryRepository;
import io.wwan13.domain.diaryimage.entity.DiaryImage;
import io.wwan13.domain.diaryimage.repository.DiaryImageRepository;
import io.wwan13.domain.image.entity.Image;
import io.wwan13.domain.image.exception.ImageNotFoundException;
import io.wwan13.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryImageService {

    private final DiaryImageRepository diaryImageRepository;
    private final DiaryRepository diaryRepository;
    private final ImageRepository imageRepository;

    public Long save(Long diaryId, String imageUrl) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new DiaryNotFoundException());
        Image image = imageRepository.save(new Image(imageUrl));

        DiaryImage diaryImage = DiaryImage.builder()
                .diary(diary)
                .image(image)
                .build();
        DiaryImage createdDiaryImage = diaryImageRepository.save(diaryImage);

        return createdDiaryImage.getId();
    }
}
