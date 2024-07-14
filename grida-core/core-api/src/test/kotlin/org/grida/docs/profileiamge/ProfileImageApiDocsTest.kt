package org.grida.docs.profileiamge

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.wwan13.api.document.snippets.ENUM
import io.wwan13.api.document.snippets.NUMBER
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.grida.docs.ApiDocsTest
import org.grida.domain.profileimage.Gender
import org.grida.domain.profileimage.ProfileImageService
import org.grida.presentation.v1.profileimage.ProfileImageController
import org.grida.presentation.v1.profileimage.dto.GenerateSampleProfileImageRequest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [ProfileImageController::class])
class ProfileImageApiDocsTest(
    private val profileImageController: ProfileImageController
) : ApiDocsTest(
    profileImageController,
    "profile-image"
) {

    @MockkBean
    private lateinit var profileImageService: ProfileImageService

    @Test
    fun `프로필 이미지 생성 API`() {
        every { profileImageService.generateSampleProfileImage(any(), any()) } returns 1L

        val api = api.post("/api/v1/user/image") {
            withBearerToken()
            requestBody(
                GenerateSampleProfileImageRequest(
                    gender = Gender.MAN,
                    age = 25,
                    hairStyle = "black parted",
                    glasses = "rounded",
                    bodyShape = "burly"
                )
            )
        }

        documentFor(api, "generate-sample-profile-image") {
            summary("샘플 프로필 이미지 생성 API")
            requestHeaders(
                "Authorization" whichMeans "인증 토큰"
            )
            requestFields(
                "gender" isTypeOf ENUM(Gender::class) whichMeans "성별",
                "age" isTypeOf NUMBER whichMeans "나이",
                "hairStyle" isTypeOf STRING whichMeans "헤어 스타일",
                "glasses" isTypeOf STRING whichMeans "안경",
                "bodyShape" isTypeOf STRING whichMeans "체형",
            )
            responseFields(
                "data.id" isTypeOf NUMBER whichMeans "생성된 프로필 이미지 ID"
            )
        }
    }

    @Test
    fun `프로필 이미지 적용 API`() {
        every { profileImageService.applyProfileImage(any(), any()) } just runs

        val api = api.post("/api/v1/user/image/apply/{profileImageId}", 1L) {
            withBearerToken()
        }

        documentFor(api, "apply-profile-image") {
            summary("프로필 이미지 적용 API")
            requestHeaders(
                "Authorization" whichMeans "인증 토큰"
            )
            pathParameters(
                "profileImageId" whichMeans "적용하려는 프로필 이미지 id"
            )
            responseFields(
                "data.id" isTypeOf NUMBER whichMeans "적용된 유저의 id"
            )
        }
    }

    @Test
    fun `프로필 이미지 교체 API`() {
        every { profileImageService.changeProfileImage(any(), any()) } returns Unit

        val api = api.post("/api/v1/user/image/change/{profileImageId}", 1) {
            withBearerToken()
        }

        documentFor(api, "change-profile-image") {
            summary("프로필 이미지 교체 API")
            requestHeaders(
                "Authorization" whichMeans "인증 토큰"
            )
            pathParameters(
                "profileImageId" whichMeans "교체하려는 프로필 이미지 id"
            )
            responseFields(
                "data.id" isTypeOf NUMBER whichMeans "적용된 유저의 id"
            )
        }
    }
}
