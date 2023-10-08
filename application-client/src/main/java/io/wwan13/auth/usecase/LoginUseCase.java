package io.wwan13.auth.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.auth.dto.LoginRequestDto;
import io.wwan13.auth.dto.LoginResponseDto;
import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.exception.IncorrectPasswordException;
import io.wwan13.domain.member.service.MemberQueryService;
import io.wwan13.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
@RequiredArgsConstructor
public class LoginUseCase {

    private final MemberQueryService memberQueryService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public LoginResponseDto execute(LoginRequestDto loginRequestDto) {
        Member member = memberQueryService.findByEmail(loginRequestDto.getEmail());
        checkPassword(member, loginRequestDto.getPassword());

        Authentication authentication = getAuthentication(loginRequestDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = tokenProvider.createToken(authentication);

        return new LoginResponseDto(jwtToken);
    }

    private Authentication getAuthentication(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }

    private void checkPassword(Member member, String requestPassword) {
        if (!isAccord(requestPassword, member.getPasswordValue())) {
            throw new IncorrectPasswordException();
        }
    }

    private boolean isAccord(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
