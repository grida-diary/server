package org.grida.docs.diaryimage

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
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
}