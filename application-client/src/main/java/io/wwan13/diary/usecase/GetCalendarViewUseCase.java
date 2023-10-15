package io.wwan13.diary.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.diary.dto.CalendarViewElement;
import io.wwan13.diary.dto.CalendarViewResponseDto;
import io.wwan13.domain.diary.entity.Diary;
import io.wwan13.domain.diary.service.DiaryQueryService;
import io.wwan13.domain.diaryimage.entity.DiaryImage;
import io.wwan13.domain.diaryimage.service.DiaryImageQueryService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetCalendarViewUseCase {

    private final DiaryQueryService diaryQueryService;
    private final DiaryImageQueryService diaryImageQueryService;

    public CalendarViewResponseDto execute(String memberEmail, LocalDate date) {
        List<Diary> diaries = diaryQueryService.findByMemberEmailAndDate(memberEmail, date);
        List<CalendarViewElement> result = diaries.stream()
                .map(diary -> {
                    DiaryImage diaryImage = diaryImageQueryService. findByDiary(diary);
                    return new CalendarViewElement(diary.getDateValue(), diaryImage.getImageUrl());
                })
                .collect(Collectors.toList());
        return new CalendarViewResponseDto(result);
    }

}
