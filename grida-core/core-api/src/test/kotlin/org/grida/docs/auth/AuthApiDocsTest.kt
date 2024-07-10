package org.grida.docs.auth

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.grida.auth.AuthTokenProvider
import org.grida.auth.AuthTokens
import org.grida.docs.ApiDocsTest
import org.grida.presentation.v1.auth.AuthController
import org.grida.presentation.v1.auth.dto.LoginRequest
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
    private lateinit var authTokenProvider: AuthTokenProvider

    @Test
    fun `로그인 API`() {

        every { authTokenProvider.provide(any(), any()) } returns AuthTokens(
            "access token", "refresh token"
        )

        val api = api.post("/api/v1/auth/login") {
            requestBody(
                LoginRequest(
                    username = "username",
                    password = "password"
                )
            )
        }

        documentFor(api, "login") {
            summary("로그인 API")
            requestFields(
                "username" isTypeOf STRING whichMeans "유저 아이디",
                "password" isTypeOf STRING whichMeans "유저 비밀번호"
            )
            responseFields(
                "data.accessToken" isTypeOf STRING whichMeans "인증 토큰",
                "data.refreshToken" isTypeOf STRING whichMeans "재발급 토큰",
            )
        }
    }
}
