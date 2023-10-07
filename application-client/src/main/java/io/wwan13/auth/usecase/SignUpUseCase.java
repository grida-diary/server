package io.wwan13.auth.usecase;

import io.wwan13.auth.dto.SignUpRequestDto;
import io.wwan13.auth.dto.SignUpResponseDto;
import io.wwan13.annotation.UseCase;
import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SignUpUseCase {

    private final MemberService memberService;

    public SignUpResponseDto execute(SignUpRequestDto signUpRequestDto) {
        Member member = memberService.save(signUpRequestDto.map());
        return SignUpResponseDto.map(member);
    }
}
