package org.grida.controller;

import io.wwan13.wintersecurity.resolve.RequestUserSubject;
import lombok.RequiredArgsConstructor;
import org.grida.dto.UserEmailResponse;
import org.grida.dto.request.CheckEmailRequest;
import org.grida.dto.request.LoginRequest;
import org.grida.dto.request.SignupRequest;
import org.grida.dto.response.CheckEmailResponse;
import org.grida.dto.response.GetRoleResponse;
import org.grida.dto.response.LoginResponse;
import org.grida.response.ApiResponse;
import org.grida.usecase.CheckEmailUseCase;
import org.grida.usecase.GetRoleUseCase;
import org.grida.usecase.LoginUseCase;
import org.grida.usecase.SignupUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class SsoController {

    private final CheckEmailUseCase checkEmailUseCase;
    private final SignupUseCase signupUseCase;
    private final LoginUseCase loginUseCase;
    private final GetRoleUseCase getRoleUseCase;

    @PostMapping("/signup/email")
    public ApiResponse checkEmail(@RequestBody CheckEmailRequest request) {
        CheckEmailResponse response = checkEmailUseCase.execute(request);
        return ApiResponse.ok(response);
    }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ApiResponse signup(@RequestBody SignupRequest request) {
        UserEmailResponse response = signupUseCase.execute(request);
        return ApiResponse.created(response);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {
        LoginResponse response = loginUseCase.execute(request);
        return ApiResponse.ok(response);
    }

    @GetMapping("/role")
    public ApiResponse getRole(@RequestUserSubject String userEmail) {
        GetRoleResponse response = getRoleUseCase.execute(userEmail);
        return ApiResponse.ok(response);
    }
}
