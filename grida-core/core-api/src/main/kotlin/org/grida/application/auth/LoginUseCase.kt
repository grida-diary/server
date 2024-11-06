package org.grida.application.auth

import org.grida.auth.AuthTokenProvider
import org.grida.auth.KakaoAuthClient
import org.grida.domain.user.LoginOption
import org.grida.domain.user.LoginPlatform
import org.grida.domain.user.UserService
import org.grida.user.KakaoUserClient
import org.springframework.stereotype.Component

@Component
class LoginUseCase(
    private val userService: UserService,
    private val authTokenProvider: AuthTokenProvider,
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoUserClient: KakaoUserClient,
) {

    fun execute(
        platform: String,
        request: LoginRequest,
    ): LoginResponse {
        val kakaoToken = kakaoAuthClient.provideAuthToken(request.code, request.state)
        val kakaoProfile = kakaoUserClient.readUserProfile(kakaoToken.accessToken)

        val loginOption = LoginOption(LoginPlatform.KAKAO, kakaoProfile.id)
        val user = userService.readUserByLoginOptionOrNull(loginOption)
            ?: userService.appendAndReturnNormalUser(kakaoProfile.name, loginOption)

        val authToken = authTokenProvider.provide(user)
        return LoginResponse(authToken.accessToken, authToken.refreshToken)
    }
}
