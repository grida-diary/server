package io.wwan13.diary.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.diary.dto.DiaryCreateRequestDto;
import io.wwan13.diary.dto.DiaryIdResponseDto;
import io.wwan13.domain.diary.exception.DiaryAlreadyExistInDateException;
import io.wwan13.domain.diary.service.DiaryQueryService;
import io.wwan13.domain.diary.service.DiaryService;
import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@UseCase
@RequiredArgsConstructor
public class CreateDiaryUseCase {

    private final DiaryService diaryService;
    private final DiaryQueryService diaryQueryService;
    private final MemberQueryService memberQueryService;

    public DiaryIdResponseDto execute(DiaryCreateRequestDto diaryCreateRequestDto, String memberEmail) {
        validate(diaryCreateRequestDto, memberEmail);

        Member member = memberQueryService.findByEmail(memberEmail);
        Long createdDiaryId = diaryService.save(diaryCreateRequestDto.map(member));

        return new DiaryIdResponseDto(createdDiaryId);
    }

    private void validate(DiaryCreateRequestDto diaryCreateRequestDto, String memberId) {
        if (!isDiaryAlreadyExistInDate(diaryCreateRequestDto.getDate(), memberId)) {
            throw new DiaryAlreadyExistInDateException();
        }
    }

    private boolean isDiaryAlreadyExistInDate(LocalDate date, String memberId) {
        return diaryQueryService.existByMemberEmailAndDate(memberId, date);
    }

}
