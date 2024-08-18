package org.grida.docs.auth

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.moreAbout
import io.wwan13.api.document.snippets.whichMeans
import io.wwan13.wintersecurity.jwt.TokenGenerator
import org.grida.auth.KakaoAuthClient
import org.grida.auth.KakaoAuthToken
import org.grida.docs.ApiDocsTest
import org.grida.domain.user.LoginOption
import org.grida.domain.user.LoginPlatform
import org.grida.domain.user.User
import org.grida.domain.user.UserService
import org.grida.presentation.v1.auth.AuthController
import org.grida.user.KakaoUserClient
import org.grida.user.KakaoUserProfile
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [AuthController::class])
class AuthApiDocsTest(
    private val authController: AuthController
) : ApiDocsTest(
    authController,
    "auth"
) {

    @MockkBean
    private lateinit var userService: UserService

    @MockkBean
    private lateinit var tokenGenerator: TokenGenerator

    @MockkBean
    private lateinit var kakaoAuthClient: KakaoAuthClient

    @MockkBean
    private lateinit var kakaoUserClient: KakaoUserClient

    @Test
    fun `카카오 로그인 API`() {

        val user = User(
            id = 1L, name = "김태완", loginOption = LoginOption(LoginPlatform.KAKAO, "123123")
        )

        every { userService.readUserByLoginOption(any()) } returns user

        every { tokenGenerator.accessToken(any()) } returns "accessToken"
        every { tokenGenerator.refreshToken(any()) } returns "refreshToken"

        val kakaoAuthToken = KakaoAuthToken("kakaoAccessToken", "kakaoRefreshToken")
        every { kakaoAuthClient.provideAuthToken(any()) } returns kakaoAuthToken

        val kakaoUserProfile = KakaoUserProfile("123123", "김태완")
        every { kakaoUserClient.readUserProfile(any()) } returns kakaoUserProfile

        val api = api.get("/api/v1/auth/kakao") {
            queryParam("code", "kakao authorization code")
        }

        documentFor(api, "kakao-login") {
            summary(
                "카카오 로그인 API" moreAbout """
                    ** local : https://kauth.kakao.com/oauth/authorize?client_id=e32f0cc35368a69966b54698b193a794&
                    redirect_uri=http://localhost:8080/api/v1/auth/login/kakao&response_type=code <br/>
                    ** live : https://kauth.kakao.com/oauth/authorize?client_id=e32f0cc35368a69966b54698b193a794&
                    redirect_uri=https://grida.today/api/v1/auth/login/kakao&response_type=code<br/>
                """.trimIndent()
            )
            queryParameters("code" whichMeans "카카오 인증 토큰")
            responseFields(
                "data.accessToken" isTypeOf STRING whichMeans "인증 토큰",
                "data.refreshToken" isTypeOf STRING whichMeans "재발급 토큰",
            )
        }
    }
}
