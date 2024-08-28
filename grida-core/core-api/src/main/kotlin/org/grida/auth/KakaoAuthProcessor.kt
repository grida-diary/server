package org.grida.auth

import org.grida.domain.user.LoginOption
import org.grida.domain.user.LoginPlatform
import org.grida.domain.user.UserService
import org.grida.user.KakaoUserClient
import org.springframework.stereotype.Component

@Component
class KakaoAuthProcessor(
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoUserClient: KakaoUserClient,
    private val userService: UserService,
    private val authTokenProvider: AuthTokenProvider
) : AuthProcessor {

    override fun process(code: String): AuthToken {
        val kakaoToken = kakaoAuthClient.provideAuthToken(code)
        val kakaoProfile = kakaoUserClient.readUserProfile(kakaoToken.accessToken)
        val loginOption = LoginOption(LoginPlatform.KAKAO, kakaoProfile.id)

        val user = userService.readUserByLoginOption(loginOption)
            ?: userService.appendAndReturnNormalUser(kakaoProfile.name, loginOption)
        return authTokenProvider.provide(user)
    }
}
