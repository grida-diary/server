package org.grida.documents;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.grida.dto.IdResponse;
import org.grida.dto.UserEmailResponse;
import org.grida.config.UserEmailResolver;
import org.grida.user.controller.UserController;
import org.grida.user.dto.request.ChangeActivateImageRequest;
import org.grida.user.dto.request.ProfileImageRequest;
import org.grida.user.dto.response.GetActivateImageResponse;
import org.grida.user.dto.response.GetProfileImageHistoryResponse;
import org.grida.user.usecase.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("User Api Documents")
public class UserApiDocuments {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserEmailResolver userEmailResolver;
    @MockBean
    OnboardingUseCase onboardingUseCase;
    @MockBean
    GetActivateImageUseCase getActivateImageUseCase;
    @MockBean
    RefreshProfileImageUseCase refreshProfileImageUseCase;
    @MockBean
    GetProfileImageHistoryUseCase getProfileImageHistoryUseCase;
    @MockBean
    ChangeActivateImageUseCase changeActivateImageUseCase;

    @Test
    void 온보딩_API() throws Exception {
        // given
        when(onboardingUseCase.execute(any(), any()))
                .thenReturn(new UserEmailResponse("user@gmaili.com"));
        when(userEmailResolver.resolveArgument(any(), any(), any(), any()))
                .thenReturn("user@gmaili.com");

        String bearerToken = "Bearer accessToken";
        ProfileImageRequest request = new ProfileImageRequest(
                23,
                "gender",
                "hairStyle",
                "hairLength",
                "hairColor",
                "glasses"
        );

        // when, then
        mockMvc.perform(
                        post("/api/user/onboarding")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", bearerToken)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(
                        document("user/onboarding",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer Token")
                                ),
                                requestFields(
                                        fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이"),
                                        fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("hairStyle").type(JsonFieldType.STRING).description("머리 스타일"),
                                        fieldWithPath("hairLength").type(JsonFieldType.STRING).description("머리 길이"),
                                        fieldWithPath("hairColor").type(JsonFieldType.STRING).description("머리 색"),
                                        fieldWithPath("glasses").type(JsonFieldType.STRING).description("안경 옵션")
                                ),
                                responseFields(
                                        fieldWithPath("timeStamp").type(JsonFieldType.STRING).description("요청 시간"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                        fieldWithPath("data.userEmail").type(JsonFieldType.STRING).description("사용자 이메일")
                                )
                        )
                )
                .andExpect(status().isOk());
    }

    @Test
    void 활성화된_프로필_이미지를_불러오는_API() throws Exception {
        // given
        when(getActivateImageUseCase.execute(any()))
                .thenReturn(new GetActivateImageResponse(
                    23,
                        "MAN",
                        "hairStyle",
                        "hairLength",
                        "hairColor",
                        "glasses",
                        "imagePath",
                        true
                ));
        when(userEmailResolver.resolveArgument(any(), any(), any(), any()))
                .thenReturn("user@gmaili.com");

        String bearerToken = "Bearer accessToken";

        // when, then
        mockMvc.perform(
                        get("/api/user/image")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", bearerToken)
                )
                .andDo(
                        document("user/getActivateImage",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer Token")
                                ),
                                responseFields(
                                        fieldWithPath("timeStamp").type(JsonFieldType.STRING).description("요청 시간"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                        fieldWithPath("data.age").type(JsonFieldType.NUMBER).description("나이"),
                                        fieldWithPath("data.gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data.hairStyle").type(JsonFieldType.STRING).description("머리 스티일"),
                                        fieldWithPath("data.hairLength").type(JsonFieldType.STRING).description("머리 길이"),
                                        fieldWithPath("data.hairColor").type(JsonFieldType.STRING).description("머리 색"),
                                        fieldWithPath("data.glasses").type(JsonFieldType.STRING).description("안경 옵션"),
                                        fieldWithPath("data.imagePath").type(JsonFieldType.STRING).description("이미지 경로"),
                                        fieldWithPath("data.canRefresh").type(JsonFieldType.BOOLEAN).description("새로고침 가능 여부")
                                )
                        )
                )
                .andExpect(status().isOk());
    }

    @Test
    void 프로필_이미지_새로고침_API() throws Exception {
        // given
        when(refreshProfileImageUseCase.execute(any(), any()))
                .thenReturn(new UserEmailResponse("user@gmaili.com"));
        when(userEmailResolver.resolveArgument(any(), any(), any(), any()))
                .thenReturn("user@gmaili.com");

        String bearerToken = "Bearer accessToken";
        ProfileImageRequest request = new ProfileImageRequest(
                23,
                "gender",
                "hairStyle",
                "hairLength",
                "hairColor",
                "glasses"
        );

        // when, then
        mockMvc.perform(
                        post("/api/user/image/refresh")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", bearerToken)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(
                        document("user/refreshProfileImage",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer Token")
                                ),
                                requestFields(
                                        fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이"),
                                        fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("hairStyle").type(JsonFieldType.STRING).description("머리 스타일"),
                                        fieldWithPath("hairLength").type(JsonFieldType.STRING).description("머리 길이"),
                                        fieldWithPath("hairColor").type(JsonFieldType.STRING).description("머리 색"),
                                        fieldWithPath("glasses").type(JsonFieldType.STRING).description("안경 옵션")
                                ),
                                responseFields(
                                        fieldWithPath("timeStamp").type(JsonFieldType.STRING).description("요청 시간"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                        fieldWithPath("data.userEmail").type(JsonFieldType.STRING).description("사용자 이메일")
                                )
                        )
                )
                .andExpect(status().isOk());
    }

    @Test
    void 프로필_이미지_히스토리를_불러오는_API() throws Exception {
        // given
        when(getProfileImageHistoryUseCase.execute(any()))
                .thenReturn(new GetProfileImageHistoryResponse(List.of(
                        new GetProfileImageHistoryResponse.HistoryElement(
                                1,
                                "https://www.imageUrl.com/imageExample1.png",
                                LocalDateTime.MIN,
                                true
                        ),
                        new GetProfileImageHistoryResponse.HistoryElement(
                                2,
                                "https://www.imageUrl.com/imageExample2.png",
                                LocalDateTime.MIN,
                                false
                        )
                )));
        when(userEmailResolver.resolveArgument(any(), any(), any(), any()))
                .thenReturn("user@gmaili.com");

        String bearerToken = "Bearer accessToken";

        // when, then
        mockMvc.perform(
                        get("/api/user/image/history")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", bearerToken)
                )
                .andDo(
                        document("user/getProfileImageHistory",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer Token")
                                ),
                                responseFields(
                                        fieldWithPath("timeStamp").type(JsonFieldType.STRING).description("요청 시간"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                        fieldWithPath("data.images[].imageId").type(JsonFieldType.NUMBER).description("이미지 ID"),
                                        fieldWithPath("data.images[].imageUrl").type(JsonFieldType.STRING).description("이미지 URL"),
                                        fieldWithPath("data.images[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("data.images[].isActivate").type(JsonFieldType.BOOLEAN).description("활성화 여부")
                                )
                        )
                )
                .andExpect(status().isOk());
    }

    @Test
    void 프로필_이미지_변경_API() throws Exception {
        // given
        when(changeActivateImageUseCase.execute(any(), any()))
                .thenReturn(new IdResponse(1));
        when(userEmailResolver.resolveArgument(any(), any(), any(), any()))
                .thenReturn("user@gmaili.com");

        ChangeActivateImageRequest request = new ChangeActivateImageRequest(1L);
        String bearerToken = "Bearer accessToken";

        // when, then
        mockMvc.perform(
                        patch("/api/user/image/change")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", bearerToken)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(
                        document("user/changeActivateProfileImage",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer Token")
                                ),
                                requestFields(
                                        fieldWithPath("targetImageId").type(JsonFieldType.NUMBER).description("변경할 이미지 ID")
                                ),
                                responseFields(
                                        fieldWithPath("timeStamp").type(JsonFieldType.STRING).description("요청 시간"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("변경된 이미지 ID")
                                )
                        )
                )
                .andExpect(status().isOk());
    }
}
