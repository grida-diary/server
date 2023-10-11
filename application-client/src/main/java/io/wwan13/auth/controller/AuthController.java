package io.wwan13.auth.controller;

import io.wwan13.auth.dto.LoginRequestDto;
import io.wwan13.auth.dto.LoginResponseDto;
import io.wwan13.auth.dto.SignUpRequestDto;
import io.wwan13.auth.dto.SignUpResponseDto;
import io.wwan13.auth.usecase.LoginUseCase;
import io.wwan13.auth.usecase.SignUpUseCase;
import io.wwan13.constant.HttpStatusCode;
import io.wwan13.auth.dto.CreateProfileImageResponseDto;
import io.wwan13.auth.dto.GetProfileImageExamplesResponseDto;
import io.wwan13.auth.usecase.CreateProfileImageUseCase;
import io.wwan13.auth.usecase.GetProfileImageExamplesUseCase;
import io.wwan13.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;
    private final GetProfileImageExamplesUseCase getProfileImageExamplesUseCase;
    private final CreateProfileImageUseCase createProfileImageUseCase;

    @PostMapping("/signup")
    public SuccessResponse<SignUpResponseDto> signUp(
            @RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto response = signUpUseCase.execute(signUpRequestDto);
        return SuccessResponse.of(HttpStatusCode.CREATED, response);
    }

    @PostMapping("/login")
    public SuccessResponse<LoginResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto response = loginUseCase.execute(loginRequestDto);
        return SuccessResponse.of(HttpStatusCode.OK, response);
    }

    @GetMapping("/{memberEmail}/profileImage")
    public SuccessResponse<GetProfileImageExamplesResponseDto> getExamples(
            @PathVariable String memberEmail) {
        GetProfileImageExamplesResponseDto response = getProfileImageExamplesUseCase.execute(memberEmail);
        return SuccessResponse.of(HttpStatusCode.OK, response);
    }

    @PostMapping("/{memberEmail}/profileImage")
    public SuccessResponse<CreateProfileImageResponseDto> createProfileImage(
            @PathVariable String memberEmail,
            @RequestBody String profileImageUrl) {
        CreateProfileImageResponseDto response = createProfileImageUseCase.execute(memberEmail, profileImageUrl);
        return SuccessResponse.of(HttpStatusCode.CREATED, response);
    }
}
