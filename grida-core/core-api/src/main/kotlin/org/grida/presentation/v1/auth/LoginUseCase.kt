package org.grida.presentation.v1.auth

import org.grida.auth.AuthProcessorSelector
import org.springframework.stereotype.Component

@Component
class LoginUseCase(
    private val authProcessorSelector: AuthProcessorSelector,
) {

    fun execute(
        platform: String,
        request: LoginRequest,
    ): LoginResponse {
        val authProcessor = authProcessorSelector.select(platform)
        val authToken = authProcessor.process(request.code, request.state)
        return LoginResponse(authToken.accessToken, authToken.refreshToken)
    }
}
