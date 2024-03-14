package org.grida.domain.diary;

import lombok.RequiredArgsConstructor;
import org.grida.datetime.DateTimePicker;
import org.grida.domain.core.ImageDetail;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryImageService {

    private final DiaryImageRepository diaryImageRepository;
    private final DateTimePicker dateTimePicker;

    public long append(long diaryId, String imagePath) {
        diaryImageRepository.save(diaryId, ImageDetail.activateImage(imagePath), dateTimePicker.now());
        return diaryId;
    }
}
