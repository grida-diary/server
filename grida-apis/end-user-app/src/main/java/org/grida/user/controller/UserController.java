package org.grida.user.controller;

import io.wwan13.wintersecurity.resolve.RequestUserSubject;
import lombok.RequiredArgsConstructor;
import org.grida.dto.IdResponse;
import org.grida.dto.UserEmailResponse;
import org.grida.response.ApiResponse;
import org.grida.user.dto.request.ChangeActivateImageRequest;
import org.grida.user.dto.request.ProfileImageRequest;
import org.grida.user.dto.response.GetActivateImageResponse;
import org.grida.user.dto.response.GetProfileImageHistoryResponse;
import org.grida.user.usecase.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final OnboardingUseCase onboardingUseCase;
    private final GetActivateImageUseCase getActivateImageUseCase;
    private final RefreshProfileImageUseCase refreshProfileImageUseCase;
    private final GetProfileImageHistoryUseCase getProfileImageHistoryUseCase;
    private final ChangeActivateImageUseCase changeActivateImageUseCase;

    @PostMapping("/onboarding")
    public ApiResponse onboarding(
            @RequestUserSubject String userEmail,
            @RequestBody ProfileImageRequest request
    ) {
        UserEmailResponse response = onboardingUseCase.execute(userEmail, request);
        return ApiResponse.ok(response);
    }

    @GetMapping("/image")
    public ApiResponse getActivateImage(@RequestUserSubject String userEmail) {
        GetActivateImageResponse response = getActivateImageUseCase.execute(userEmail);
        return ApiResponse.ok(response);
    }

    @PostMapping("/image/refresh")
    public ApiResponse refreshProfileImage(
            @RequestUserSubject String userEmail,
            @RequestBody ProfileImageRequest request
    ) {
        UserEmailResponse response = refreshProfileImageUseCase.execute(userEmail, request);
        return ApiResponse.ok(response);
    }

    @GetMapping("/image/history")
    public ApiResponse getProfileImageHistory(@RequestUserSubject String userEmail) {
        GetProfileImageHistoryResponse response = getProfileImageHistoryUseCase.execute(userEmail);
        return ApiResponse.ok(response);
    }

    @PatchMapping("/image/change")
    public ApiResponse changeActivateProfileImage(
            @RequestUserSubject String userEmail,
            @RequestBody ChangeActivateImageRequest request
    ) {
        IdResponse response = changeActivateImageUseCase.execute(userEmail, request);
        return ApiResponse.ok(response);
    }
}
