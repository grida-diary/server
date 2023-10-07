package io.wwan13.auth.usecase;

import io.wwan13.auth.dto.SignUpRequestDto;
import io.wwan13.auth.dto.SignUpResponseDto;
import io.wwan13.annotation.UseCase;
import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
@RequiredArgsConstructor
public class SignUpUseCase {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto execute(SignUpRequestDto signUpRequestDto) {
        Member member = memberService.save(signUpRequestDto.map(passwordEncoder));
        return SignUpResponseDto.map(member);
    }
}
