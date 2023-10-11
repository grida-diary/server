package io.wwan13.auth.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.service.MemberQueryService;
import io.wwan13.auth.dto.GetProfileImageExamplesResponseDto;
import io.wwan13.openai.model.MemberProfile;
import io.wwan13.openai.processor.ImageGenerationProcessor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetProfileImageExamplesUseCase {

    private final MemberQueryService memberQueryService;
    private final ImageGenerationProcessor imageGenerationProcessor;

    public GetProfileImageExamplesResponseDto execute(String memberEmail) {
        Member member = memberQueryService.findByEmail(memberEmail);
        MemberProfile memberProfile = MemberProfile.builder()
                .gender(member.getGenderName())
                .age(member.getAgeValue())
                .build();

        List<String> examples = imageGenerationProcessor.proceed(memberProfile);
        return new GetProfileImageExamplesResponseDto(examples);
    }
}
