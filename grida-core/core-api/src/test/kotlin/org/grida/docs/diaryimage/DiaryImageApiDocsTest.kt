package org.grida.docs.diaryimage

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.wwan13.api.document.snippets.NUMBER
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.grida.docs.ApiDocsTest
import org.grida.domain.diaryimage.DiaryImageService
import org.grida.presentation.v1.diaryimage.DiaryImageController
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [DiaryImageController::class])
class DiaryImageApiDocsTest(
    private val diaryImageController: DiaryImageController
) : ApiDocsTest(
    diaryImageController,
    "diary-image"
) {

    @MockkBean
    private lateinit var diaryImageService: DiaryImageService

    @Test
    fun `일기 이미지 생성 API`() {
        every { diaryImageService.generateDiaryImage(any(), any()) } returns 1L

        val api = api.post("/api/v1/diary/{diaryId}/image", 1L) {
            withBearerToken()
        }

        documentFor(api, "generate-diary-image") {
            summary("일기 이미지 생성 API")
            requestHeaders(
                "Authorization" whichMeans "인증 토큰"
            )
            pathParameters(
                "diaryId" whichMeans "생성하려는 일기의 ID"
            )
            responseFields(
                "data.id" isTypeOf NUMBER whichMeans "생성된 일기 이미지 ID"
            )
        }
    }

    @Test
    fun `일기 이미지 적용 API`() {
        every { diaryImageService.applyDiaryImage(any(), any(), any()) } just runs

        val api = api.post("/api/v1/diary/{diaryId}/image/{diaryImageId}", 1L, 1L) {
            withBearerToken()
        }

        documentFor(api, "apply-diary-image") {
            summary("일기 이미지 적용 API")
            requestHeaders(
                "Authorization" whichMeans "인증 토큰"
            )
            pathParameters(
                "diaryId" whichMeans "적용하려는 일기의 ID",
                "diaryImageId" whichMeans "적용하려는 일기 이미지의 ID",
            )
            responseFields(
                "data.id" isTypeOf NUMBER whichMeans "적용된 일기 ID"
            )
        }
    }

    @Test
    fun `대표 일기 이미지 수정 API`() {
        every { diaryImageService.changeDiaryImage(any(), any(), any()) } just runs

        val api = api.post("/api/v1/diary/{diaryId}/image/{diaryImageId}/change", 1L, 1L) {
            withBearerToken()
        }

        documentFor(api, "change-diary-image") {
            summary("대표 일기 이미지 수정 API")
            requestHeaders(
                "Authorization" whichMeans "인증 토큰"
            )
            pathParameters(
                "diaryId" whichMeans "적용하려는 일기의 ID",
                "diaryImageId" whichMeans "수정하려는 일기 이미지의 ID",
            )
            responseFields(
                "data.id" isTypeOf NUMBER whichMeans "적용된 일기 ID"
            )
        }
    }
}
