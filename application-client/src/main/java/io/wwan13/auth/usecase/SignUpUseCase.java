package io.wwan13.auth.usecase;

import io.wwan13.auth.dto.SignUpRequestDto;
import io.wwan13.auth.dto.SignUpResponseDto;
import io.wwan13.annotation.UseCase;
import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.exception.EmailAlreadyExistsException;
import io.wwan13.domain.member.exception.PasswordCheckFailException;
import io.wwan13.domain.member.service.MemberQueryService;
import io.wwan13.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
@RequiredArgsConstructor
public class SignUpUseCase {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto execute(SignUpRequestDto signUpRequestDto) {
        validate(signUpRequestDto);
        String createdMemberEmail = memberService.save(signUpRequestDto.map(passwordEncoder));
        return new SignUpResponseDto(createdMemberEmail);
    }

    private void validate(SignUpRequestDto signUpRequestDto) {
        if (isExistEmail(signUpRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException();
        } else if (
                !isAccordPassword(signUpRequestDto.getPassword(), signUpRequestDto.getPasswordCheck())) {
            throw new PasswordCheckFailException();
        }
    }

    private boolean isExistEmail(String email) {
        return memberQueryService.existByEmail(email);
    }

    private boolean isAccordPassword(String password, String passwordCheck) {
        return password.equals(passwordCheck);
    }
}
