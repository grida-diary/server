package org.grida.presentation.v1.diaryimage

import org.grida.api.dto.IdResponse
import org.grida.domain.diaryimage.DiaryImageService
import org.springframework.stereotype.Component

@Component
class ApplyDiaryImageUseCase(
    private val diaryImageService: DiaryImageService,
) {

    fun execute(
        userId: Long,
        diaryId: Long,
        diaryImageId: Long,
    ): IdResponse {
        diaryImageService.applyDiaryImage(diaryImageId, diaryId, userId)
        return IdResponse(diaryId)
    }
}
