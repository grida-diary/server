package io.wwan13.diary.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.diary.dto.DiaryImageExampleResponseDto;
import io.wwan13.domain.diary.entity.Diary;
import io.wwan13.domain.diary.service.DiaryQueryService;
import io.wwan13.domain.diary.service.DiaryService;
import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.service.MemberQueryService;
import io.wwan13.domain.profileimage.entity.ProfileImage;
import io.wwan13.domain.profileimage.service.ProfileImageQueryService;
import io.wwan13.openai.model.MemberProfile;
import io.wwan13.openai.model.ProcessResult;
import io.wwan13.openai.processor.ChatCompletionProcessor;
import io.wwan13.openai.processor.ImageGenerationProcessor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetDiaryImageExampleUseCase {

    private final DiaryService diaryService;
    private final MemberQueryService memberQueryService;
    private final DiaryQueryService diaryQueryService;
    private final ProfileImageQueryService profileImageQueryService;
    private final ChatCompletionProcessor chatCompletionProcessor;
    private final ImageGenerationProcessor imageGenerationProcessor;

    public DiaryImageExampleResponseDto execute(Long diaryId, String memberEmail) {
        Diary diary = diaryQueryService.findById(diaryId);
        Member member = memberQueryService.findByEmail(memberEmail);

        String exampleImageUrl = generateDiaryImageUrl(diary.getContentValue(), member);
        Integer refreshChance = diaryService.refresh(diaryId);

        return new DiaryImageExampleResponseDto(exampleImageUrl, refreshChance);
    }

    private String generateDiaryImageUrl(String content, Member member) {
        MemberProfile memberProfile = createMemberProfile(member);
        ProcessResult processResult = chatCompletionProcessor.proceed(content);
        return imageGenerationProcessor.proceed(memberProfile, processResult);
    }

    private MemberProfile createMemberProfile(Member member) {
        ProfileImage profileImage = profileImageQueryService.findByMember(member);

        return MemberProfile.builder()
                .gender(member.getGenderName())
                .age(member.getAgeValue())
                .profileImageUrl(profileImage.getImageUrl())
                .build();
    }

}
