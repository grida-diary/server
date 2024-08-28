package org.grida.docs.auth

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.moreAbout
import io.wwan13.api.document.snippets.whichMeans
import org.grida.auth.AuthProcessorSelector
import org.grida.docs.ApiDocsTest
import org.grida.docs.auth.stub.StubAuthProcessor
import org.grida.presentation.v1.auth.AuthController
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
    private lateinit var authProcessorSelector: AuthProcessorSelector

    @Test
    fun `인증 토큰 발급 API`() {

        every { authProcessorSelector.select(any()) } returns StubAuthProcessor()

        val api = api.get("/api/v1/auth") {
            queryParam("platform", "oauth2 platform")
            queryParam("code", "kakao authorization code")
        }

        documentFor(api, "provide-auth-token") {
            summary(
                "인증 토큰 발급 API" moreAbout """
                    kakao <br/>
                    ** local : https://kauth.kakao.com/oauth/authorize?client_id=e32f0cc35368a69966b54698b193a794&
                    redirect_uri=http://localhost:8080/api/v1/auth/login/kakao&response_type=code <br/>
                    ** live : https://kauth.kakao.com/oauth/authorize?client_id=e32f0cc35368a69966b54698b193a794&
                    redirect_uri=https://grida.today/api/v1/auth/login/kakao&response_type=code<br/>
                """.trimIndent()
                    .lineSequence()
                    .map { it.trimEnd() }
                    .joinToString("")
            )
            queryParameters(
                "platform" whichMeans "oauth2 플랫폼 [kakao/**]",
                "code" whichMeans "인증 토큰"
            )
            responseFields(
                "data.accessToken" isTypeOf STRING whichMeans "인증 토큰",
                "data.refreshToken" isTypeOf STRING whichMeans "재발급 토큰",
            )
        }
    }
}
