package org.grida.presentation.v1

import org.grida.api.ApiResponse
import org.grida.application.auth.LoginRequest
import org.grida.application.auth.LoginResponse
import org.grida.application.auth.LoginUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val loginUseCase: LoginUseCase
) {

    @PostMapping
    fun provideAuthToken(
        @RequestParam("platform") platform: String,
        @RequestBody request: LoginRequest
    ): ApiResponse<LoginResponse> {
        val response = loginUseCase.execute(platform, request)
        return ApiResponse.success(response)
    }
}
