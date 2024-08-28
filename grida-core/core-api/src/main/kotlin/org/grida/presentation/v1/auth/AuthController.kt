package org.grida.presentation.v1.auth

import org.grida.api.ApiResponse
import org.grida.auth.AuthProcessorSelector
import org.grida.presentation.v1.auth.dto.LoginResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authProcessorSelector: AuthProcessorSelector
) {

    @GetMapping
    fun provideAuthToken(
        @RequestParam("platform") platform: String,
        @RequestParam("code") code: String
    ): ApiResponse<LoginResponse> {
        val authProcessor = authProcessorSelector.select(platform)
        val authToken = authProcessor.process(code)
        val response = LoginResponse.from(authToken)
        return ApiResponse.success(response)
    }
}
