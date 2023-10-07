package io.wwan13.auth.controller;

import io.wwan13.auth.dto.SignUpRequestDto;
import io.wwan13.auth.dto.SignUpResponseDto;
import io.wwan13.auth.usecase.SignUpUseCase;
import io.wwan13.constant.HttpStatusCode;
import io.wwan13.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignUpUseCase signUpUseCase;

    public SuccessResponse<SignUpResponseDto> signUp(
            @RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto response = signUpUseCase.execute(signUpRequestDto);
        return SuccessResponse.of(HttpStatusCode.CREATED, response);
    }
}
