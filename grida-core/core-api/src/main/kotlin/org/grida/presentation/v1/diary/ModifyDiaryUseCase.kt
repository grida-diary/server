package org.grida.presentation.v1.diary

import org.grida.api.dto.IdResponse
import org.grida.domain.diary.DiaryService
import org.grida.domain.diaryimage.DiaryImageService
import org.springframework.stereotype.Component

@Component
class ModifyDiaryUseCase(
    private val diaryService: DiaryService,
    private val diaryImageService: DiaryImageService,
) {

    fun execute(
        userId: Long,
        diaryId: Long,
        request: DiaryModifyRequest,
    ): IdResponse {
        val modifiedDiaryId = diaryService.modify(
            diaryId,
            userId,
            request.content
        )
        return IdResponse(modifiedDiaryId)
    }
}
