package io.wwan13.domain.diary.service;

import io.wwan13.domain.diary.entity.Diary;
import io.wwan13.domain.diary.exception.DiaryNotFoundException;
import io.wwan13.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryQueryService {

    private final DiaryRepository diaryRepository;

    public Boolean existByMemberEmailAndDate(String memberEmail, LocalDate date) {
        return diaryRepository.existsByOwnerEmailAndDateDate(memberEmail, date);
    }

    public Diary findById(Long diaryId) {
        return diaryRepository.findById(diaryId)
                .orElseThrow(() -> new DiaryNotFoundException());
    }

}
