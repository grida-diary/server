package io.wwan13.domain.diary.service;

import io.wwan13.domain.diary.entity.Diary;
import io.wwan13.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Diary transientSave(Diary diary) {
        return diaryRepository.save(diary);
    }

}
