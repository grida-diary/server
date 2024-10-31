package org.grida.presentation.v1.diaryimage

import org.grida.api.dto.IdResponse
import org.grida.domain.diaryimage.DiaryImageService
import org.springframework.stereotype.Component

@Component
class ChangeDiaryImageUseCase(
    private val diaryImageService: DiaryImageService,
) {

    fun execute(
        userId: Long,
        diaryId: Long,
        diaryImageId: Long,
    ): IdResponse {
        diaryImageService.changeDiaryImage(diaryImageId, diaryId, userId)
        return IdResponse(diaryId)
    }
}
