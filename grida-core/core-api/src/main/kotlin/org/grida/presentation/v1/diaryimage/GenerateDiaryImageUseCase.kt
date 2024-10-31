package org.grida.presentation.v1.diaryimage

import org.grida.api.dto.IdResponse
import org.grida.domain.diaryimage.DiaryImageService
import org.springframework.stereotype.Component

@Component
class GenerateDiaryImageUseCase(
    private val diaryImageService: DiaryImageService
) {

    fun execute(
        userId: Long,
        diaryId: Long
    ): IdResponse {
        val tmpDiaryImage = "diaryImage"
        val generatedDiaryImageId = diaryImageService.generateDiaryImage(diaryId, userId, tmpDiaryImage)
        return IdResponse(generatedDiaryImageId)
    }
}
