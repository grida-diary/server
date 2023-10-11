package io.wwan13.diary.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.diary.dto.DiaryCreateRequestDto;
import io.wwan13.diary.dto.DiaryCreateResponseDto;
import io.wwan13.domain.diary.exception.DiaryAlreadyExistInDateException;
import io.wwan13.domain.diary.service.DiaryQueryService;
import io.wwan13.domain.diary.service.DiaryService;
import io.wwan13.domain.diaryimage.service.DiaryImageService;
import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.service.MemberQueryService;
import io.wwan13.openai.model.MemberProfile;
import io.wwan13.openai.model.ProcessResult;
import io.wwan13.openai.processor.ChatCompletionProcessor;
import io.wwan13.openai.processor.ImageGenerationProcessor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class CreateDiaryUseCase {

    private final DiaryService diaryService;
    private final DiaryQueryService diaryQueryService;
    private final DiaryImageService diaryImageService;
    private final MemberQueryService memberQueryService;
    private final ChatCompletionProcessor chatCompletionProcessor;
    private final ImageGenerationProcessor imageGenerationProcessor;

    public DiaryCreateResponseDto execute(DiaryCreateRequestDto diaryCreateRequestDto, String memberEmail) {
        validate(diaryCreateRequestDto, memberEmail);
        Member member = memberQueryService.findByEmail(memberEmail);
        Long createdDiaryId = diaryService.save(diaryCreateRequestDto.map(member));

        String diaryImageUrl = generateDiaryImageUrl(diaryCreateRequestDto.getContent(), member);
        diaryImageService.save(createdDiaryId, diaryImageUrl);

        return new DiaryCreateResponseDto(createdDiaryId);
    }

    private String generateDiaryImageUrl(String content, Member member) {
        ProcessResult processResult = chatCompletionProcessor.proceed(content);

        MemberProfile memberProfile = MemberProfile.builder()
                .gender(member.getGenderName())
                .age(member.getAgeValue())
                .build();
        return imageGenerationProcessor.proceed(memberProfile, processResult);
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
