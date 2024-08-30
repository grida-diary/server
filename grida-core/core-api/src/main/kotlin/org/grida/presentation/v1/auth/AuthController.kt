package org.grida.presentation.v1.auth

import org.grida.api.ApiResponse
import org.grida.auth.AuthProcessorSelector
import org.grida.presentation.v1.auth.dto.LoginRequest
import org.grida.presentation.v1.auth.dto.LoginResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authProcessorSelector: AuthProcessorSelector
) {

    @PostMapping
    fun provideAuthToken(
        @RequestParam("platform") platform: String,
        @RequestBody request: LoginRequest
    ): ApiResponse<LoginResponse> {
        val authProcessor = authProcessorSelector.select(platform)
        val authToken = authProcessor.process(request.code, request.state)
        val response = LoginResponse.from(authToken)
        return ApiResponse.success(response)
    }
}
