package org.grida.docs.user

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.wwan13.wintersecurity.passwordencoder.PasswordEncoder
import org.grida.docs.RestDocsTest
import org.grida.docs.RestDocsUtils
import org.grida.domain.user.UserService
import org.grida.presentation.v1.user.UserController
import org.grida.presentation.v1.user.dto.SignInRequest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [UserController::class])
class UserApisDocsTest : RestDocsTest() {

    @MockkBean
    private lateinit var userService: UserService

    @MockkBean
    private lateinit var passwordEncoder: PasswordEncoder

    @Test
    fun `회원가입 API`() {

        val request = SignInRequest(
            username = "username",
            password = "raw password",
            passwordConfirm = "raw password",
            nickname = "nickname"
        )

        every { userService.appendNormalUser(any(), any()) } returns 1L
        every { passwordEncoder.encode(any()) } returns "encoded password"

        mockMvc
            .perform(
                post("/api/v1/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andDo(
                document(
                    "signin",
                    RestDocsUtils.requestPreprocessor(),
                    RestDocsUtils.responsePreprocessor(),
                    requestFields(
                        fieldWithPath("username")
                            .type(JsonFieldType.STRING)
                            .description("유저 아이디"),

                        fieldWithPath("password")
                            .type(JsonFieldType.STRING)
                            .description("유저 비밀번호"),

                        fieldWithPath("passwordConfirm")
                            .type(JsonFieldType.STRING)
                            .description("비밀번호 확인"),

                        fieldWithPath("nickname")
                            .type(JsonFieldType.STRING)
                            .description("닉네임"),
                    ),
                    responseFields(
                        fieldWithPath("status")
                            .type(JsonFieldType.STRING)
                            .description("응답 상태"),
                        fieldWithPath("timestamp")
                            .type(JsonFieldType.STRING)
                            .description("응답 시간"),
                        fieldWithPath("data.id")
                            .type(JsonFieldType.NUMBER)
                            .description("생성된 user id"),
                    )
                )
            )
    }
}
