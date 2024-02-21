package org.grida.documents;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.grida.controller.SsoController;
import org.grida.dto.UserEmailResponse;
import org.grida.dto.request.CheckEmailRequest;
import org.grida.dto.request.LoginRequest;
import org.grida.dto.request.SignupRequest;
import org.grida.dto.response.CheckEmailResponse;
import org.grida.dto.response.GetRoleResponse;
import org.grida.dto.response.LoginResponse;
import org.grida.filter.UserEmailResolver;
import org.grida.usecase.CheckEmailUseCase;
import org.grida.usecase.GetRoleUseCase;
import org.grida.usecase.LoginUseCase;
import org.grida.usecase.SignupUseCase;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(SsoController.class)
@ActiveProfiles("test")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Sso Api Documents")
class SsoApiDocuments {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    SignupUseCase signupUseCase;
    @MockBean
    CheckEmailUseCase checkEmailUseCase;
    @MockBean
    LoginUseCase loginUseCase;
    @MockBean
    GetRoleUseCase getRoleUseCase;
    @MockBean
    UserEmailResolver userEmailResolver;

    @Test
    void 회원가입_API() throws Exception {
        // given
        when(signupUseCase.execute(any()))
                .thenReturn(new UserEmailResponse("user@gmail.com"));

        SignupRequest request = new SignupRequest(
                "user@gmail.com",
                "password",
                "password",
                "nickname"
        );

        // when, then
        mockMvc.perform(
                        post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andDo(
                        document("auth/signup",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("passwordCheck").type(JsonFieldType.STRING).description("비밀번호 확인"),
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임")
                                ),
                                responseFields(
                                        fieldWithPath("timeStamp").type(JsonFieldType.STRING).description("요청 시간"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                        fieldWithPath("data.userEmail").type(JsonFieldType.STRING).description("생성된 이메일")
                                )
                        )
                )
                .andExpect(status().isCreated());
    }

    @Test
    void 이메일_확인_API() throws Exception {
        // given
        when(checkEmailUseCase.execute(any()))
                .thenReturn(new CheckEmailResponse("user@gmail.com", true));

        CheckEmailRequest request = new CheckEmailRequest("user@gmail.com");

        // when, then
        mockMvc.perform(
                        post("/api/auth/signup/email")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(
                        document("auth/checkEmail",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
                                ),
                                responseFields(
                                        fieldWithPath("timeStamp").type(JsonFieldType.STRING).description("요청 시간"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.isUsable").type(JsonFieldType.BOOLEAN).description("이메일의 사용 가능 여부")
                                )
                        )
                )
                .andExpect(status().isOk());
    }

    @Test
    void 로그인_API() throws Exception {
        // given
        when(loginUseCase.execute(any()))
                .thenReturn(new LoginResponse("accessToken", "refreshToken", false));

        LoginRequest request = new LoginRequest("user@gmail.com", "password");

        // when, then
        mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(
                        document("auth/login",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                                ),
                                responseFields(
                                        fieldWithPath("timeStamp").type(JsonFieldType.STRING).description("요청 시간"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                        fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("accessToken"),
                                        fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("refreshToken"),
                                        fieldWithPath("data.needOnboarding").type(JsonFieldType.BOOLEAN).description("온보딩 과정 필요 여부")
                                )
                        )
                )
                .andExpect(status().isOk());
    }

    @Test
    void 사용자_권한_확인_API() throws Exception {
        // given
        when(userEmailResolver.supportsParameter(any()))
                .thenReturn(true);
        when(userEmailResolver.resolveArgument(any(), any(), any(), any()))
                .thenReturn("user@gmail.com");

        when(getRoleUseCase.execute(any()))
                .thenReturn(new GetRoleResponse("ROLE_BASIC_USER"));

        String bearerToken = "Bearer accessToken";

        // when, then
        mockMvc.perform(
                        get("/api/auth/role")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", bearerToken)
                )
                .andDo(
                        document("auth/role",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer Token")
                                ),
                                responseFields(
                                        fieldWithPath("timeStamp").type(JsonFieldType.STRING).description("요청 시간"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                        fieldWithPath("data.role").type(JsonFieldType.STRING).description("사용자 권한")
                                )
                        )
                )
                .andExpect(status().isOk());
    }
}