package org.grida.presentation.v1.auth

import io.wwan13.wintersecurity.jwt.TokenGenerator
import org.grida.api.ApiResponse
import org.grida.auth.KakaoAuthClient
import org.grida.config.TokenPayload
import org.grida.domain.user.LoginOption
import org.grida.domain.user.LoginPlatform
import org.grida.domain.user.UserService
import org.grida.presentation.v1.auth.dto.LoginResponse
import org.grida.user.KakaoUserClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val userService: UserService,
    private val tokenGenerator: TokenGenerator,
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoUserClient: KakaoUserClient
) {

    @GetMapping("/kakao")
    fun kakaoLogin(
        @RequestParam("code") kakaoAuthCode: String
    ): ApiResponse<LoginResponse> {
        val kakaoToken = kakaoAuthClient.provideAuthToken(kakaoAuthCode)
        val kakaoProfile = kakaoUserClient.readUserProfile(kakaoToken.accessToken)

        val loginOption = LoginOption(LoginPlatform.KAKAO, kakaoProfile.id)
        val user = userService.readUserByLoginOption(loginOption)
            ?: userService.appendAndReturnNormalUser(kakaoProfile.name, loginOption)

        val tokenPayload = TokenPayload(user.id, user.role)
        val response = LoginResponse(
            accessToken = tokenGenerator.accessToken(tokenPayload),
            refreshToken = tokenGenerator.refreshToken(tokenPayload)
        )
        return ApiResponse.success(response)
    }
}
