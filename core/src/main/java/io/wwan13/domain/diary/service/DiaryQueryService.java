package io.wwan13.domain.diary.service;

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

}
