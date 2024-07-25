package org.grida.docs.profileimage

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.wwan13.api.document.snippets.BOOLEAN
import io.wwan13.api.document.snippets.DATETIME
import io.wwan13.api.document.snippets.ENUM
import io.wwan13.api.document.snippets.NUMBER
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.grida.docs.ApiDocsTest
import org.grida.domain.image.Image
import org.grida.domain.image.ImageStatus
import org.grida.domain.profileimage.Appearance
import org.grida.domain.profileimage.Gender
import org.grida.domain.profileimage.ProfileImage
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

    @Test
    fun `활성화된 프로필 이미지가 존재하는지 확인하는 API`() {
        every { profileImageService.hasActivateProfileImage(any()) } returns true

        val api = api.get("/api/v1/user/image/exists") {
            withBearerToken()
        }

        documentFor(api, "profile-image-exists") {
            summary("활성화된 프로필 이미지가 존재하는지 확인하는 API")
            requestHeaders(
                "Authorization" whichMeans "인증 토큰"
            )
            responseFields(
                "data.result" isTypeOf BOOLEAN whichMeans "확인 결과"
            )
        }
    }

    @Test
    fun `활성화된 프로필 이미지를 불러오는 API`() {
        val profileImage = ProfileImage(
            id = 1L,
            userId = 1L,
            image = Image("https://imageUrl.com"),
            appearance = Appearance(Gender.MAN, 21, "hair style", "glasses", "body shape")
        )
        every { profileImageService.readActivateProfileImage(any()) } returns profileImage

        val api = api.get("/api/v1/user/image") {
            withBearerToken()
        }

        documentFor(api, "profile-image-exists") {
            summary("활성화된 프로필 이미지를 불러오는 API")
            requestHeaders(
                "Authorization" whichMeans "인증 토큰"
            )
            responseFields(
                "data.imageId" isTypeOf NUMBER whichMeans "이미지 ID",
                "data.imageUrl" isTypeOf STRING whichMeans "이미지 URL",
                "data.status" isTypeOf ENUM(ImageStatus::class) whichMeans "이미지 활성화 상태",
                "data.createdAt" isTypeOf DATETIME whichMeans "이미지 생성 시간",
                "data.gender" isTypeOf ENUM(Gender::class) whichMeans "성별",
                "data.age" isTypeOf NUMBER whichMeans "나이",
                "data.hairStyle" isTypeOf STRING whichMeans "머리 스타일",
                "data.glasses" isTypeOf STRING whichMeans "안경 유무 / 모양",
                "data.bodyShape" isTypeOf STRING whichMeans "체형",
            )
        }
    }

    @Test
    fun `이전 프로필 이미지들을 모두 불러오는 API`() {
        val profileImages = listOf(
            ProfileImage(
                id = 1L,
                userId = 1L,
                image = Image("https://imageUrl1.com"),
                appearance = Appearance(Gender.MAN, 21, "hair style1", "glasses1", "body shape1")
            ),
            ProfileImage(
                id = 2L,
                userId = 1L,
                image = Image("https://imageUrl2.com"),
                appearance = Appearance(Gender.MAN, 21, "hair style2", "glasses2", "body shape2")
            )
        )
        every { profileImageService.readProfileImageHistory(any()) } returns profileImages

        val api = api.get("/api/v1/user/image/history") {
            withBearerToken()
        }

        documentFor(api, "profile-image-exists") {
            summary("활성화된 프로필 이미지를 불러오는 API")
            requestHeaders(
                "Authorization" whichMeans "인증 토큰"
            )
            responseFields(
                "data.count" isTypeOf NUMBER whichMeans "프로필 이미지 개수",
                "data.profileImages[].imageId" isTypeOf NUMBER whichMeans "이미지 ID",
                "data.profileImages[].imageUrl" isTypeOf STRING whichMeans "이미지 URL",
                "data.profileImages[].status" isTypeOf ENUM(ImageStatus::class) whichMeans "이미지 활성화 상태",
                "data.profileImages[].createdAt" isTypeOf DATETIME whichMeans "이미지 생성 시간",
                "data.profileImages[].gender" isTypeOf ENUM(Gender::class) whichMeans "성별",
                "data.profileImages[].age" isTypeOf NUMBER whichMeans "나이",
                "data.profileImages[].hairStyle" isTypeOf STRING whichMeans "머리 스타일",
                "data.profileImages[].glasses" isTypeOf STRING whichMeans "안경 유무 / 모양",
                "data.profileImages[].bodyShape" isTypeOf STRING whichMeans "체형",
            )
        }
    }
}
