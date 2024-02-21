package org.grida.user.controller;

import lombok.RequiredArgsConstructor;
import org.grida.dto.UserEmailResponse;
import org.grida.response.ApiResponse;
import org.grida.user.dto.request.ProfileImageRequest;
import org.grida.user.dto.response.GetActivateImageResponse;
import org.grida.user.dto.response.GetProfileImageHistoryResponse;
import org.grida.user.usecase.GetActivateImageUseCase;
import org.grida.user.usecase.GetProfileImageHistoryUseCase;
import org.grida.user.usecase.OnboardingUseCase;
import org.grida.user.usecase.RefreshProfileImageUseCase;
import org.grida.util.RequestUserEmail;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final OnboardingUseCase onboardingUseCase;
    private final GetActivateImageUseCase getActivateImageUseCase;
    private final RefreshProfileImageUseCase refreshProfileImageUseCase;
    private final GetProfileImageHistoryUseCase getProfileImageHistoryUseCase;

    @PostMapping("/onboarding")
    public ApiResponse onboarding(
            @RequestUserEmail String userEmail,
            @RequestBody ProfileImageRequest request
    ) {
        UserEmailResponse response = onboardingUseCase.execute(userEmail, request);
        return ApiResponse.ok(response);
    }

    @GetMapping("/image")
    public ApiResponse getActivateImage(@RequestUserEmail String userEmail) {
        GetActivateImageResponse response = getActivateImageUseCase.execute(userEmail);
        return ApiResponse.ok(response);
    }

    @PostMapping("/image/refresh")
    public ApiResponse refreshProfileImage(
            @RequestUserEmail String userEmail,
            @RequestBody ProfileImageRequest request
    ) {
        UserEmailResponse response = refreshProfileImageUseCase.execute(userEmail, request);
        return ApiResponse.ok(response);
    }

    @GetMapping("/image/history")
    public ApiResponse getProfileImageHistory(@RequestUserEmail String userEmail) {
        GetProfileImageHistoryResponse response = getProfileImageHistoryUseCase.execute(userEmail);
        return ApiResponse.ok(response);
    }
}
