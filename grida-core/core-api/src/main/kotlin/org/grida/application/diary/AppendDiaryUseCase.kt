package org.grida.application.diary

import org.grida.api.dto.IdResponse
import org.grida.datetime.DateTimeUtil
import org.grida.domain.diary.Diary
import org.grida.domain.diary.DiaryScope
import org.grida.domain.diary.DiaryService
import org.springframework.stereotype.Component

@Component
class AppendDiaryUseCase(
    private val diaryService: DiaryService,
) {

    fun execute(
        userId: Long,
        request: DiaryRequest,
    ): IdResponse {
        val diary = Diary(
            userId = userId,
            content = request.content,
            targetDate = DateTimeUtil.toLocalDate(request.targetDate),
            scope = DiaryScope.PUBLIC
        )
        val diaryId = diaryService.appendDiary(diary)

        return IdResponse(diaryId)
    }
}
