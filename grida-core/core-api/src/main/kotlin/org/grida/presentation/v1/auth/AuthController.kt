package org.grida.presentation.v1.auth

import org.grida.api.ApiResponse
import org.grida.auth.AuthTokenProvider
import org.grida.auth.AuthTokens
import org.grida.presentation.v1.auth.dto.LoginRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/v1")
class AuthController(
    private val authTokenProvider: AuthTokenProvider
) {

    @PostMapping("/login")
    fun login(
        request: LoginRequest
    ): ApiResponse<AuthTokens> {
        val authTokens = authTokenProvider.provide(request.username, request.password)
        return ApiResponse.success(authTokens)
    }
}
