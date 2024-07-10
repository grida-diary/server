package org.grida.docs.user

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.wwan13.api.document.snippets.NUMBER
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import io.wwan13.wintersecurity.passwordencoder.PasswordEncoder
import org.grida.docs.ApiDocsTest
import org.grida.domain.user.UserService
import org.grida.presentation.v1.user.UserController
import org.grida.presentation.v1.user.dto.SignInRequest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [UserController::class])
class UserApiDocsTest(
    private val userController: UserController
) : ApiDocsTest(
    userController,
    "user"
) {

    @MockkBean
    private lateinit var userService: UserService

    @MockkBean
    private lateinit var passwordEncoder: PasswordEncoder

    @Test
    fun `회원가입 API`() {

        every { userService.appendNormalUser(any(), any()) } returns 1L
        every { passwordEncoder.encode(any()) } returns "encoded password"

        val api = api.post("/api/v1/user") {
            requestBody(
                SignInRequest(
                    username = "username",
                    password = "raw password",
                    passwordConfirm = "raw password",
                    nickname = "nickname"
                )
            )
        }

        documentFor(api, "sign-in") {
            summary("회원가입 API")
            requestFields(
                "username" isTypeOf STRING whichMeans "유저 아이디",
                "password" isTypeOf STRING whichMeans "유저 비밀번호",
                "passwordConfirm" isTypeOf STRING whichMeans "비밀번호 확인",
                "nickname" isTypeOf STRING whichMeans "유저 닉네임"
            )
            responseFields(
                "data.id" isTypeOf NUMBER whichMeans "생성된 사용자 ID"
            )
        }
    }
}
