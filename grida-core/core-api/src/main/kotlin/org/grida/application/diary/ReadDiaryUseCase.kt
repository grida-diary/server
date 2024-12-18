package org.grida.application.diary

import org.grida.datetime.DateTimeUtil
import org.grida.domain.diary.DiaryService
import org.grida.domain.diaryimage.DiaryImageService
import org.springframework.stereotype.Component

@Component
class ReadDiaryUseCase(
    private val diaryService: DiaryService,
    private val diaryImageService: DiaryImageService,
) {

    fun execute(
        userId: Long,
        diaryId: Long,
    ): DiaryResponse {
        val diary = diaryService.readDiary(diaryId, userId)
        val diaryImage = diaryImageService.readByDiaryId(diaryId)
        val remainCount = diaryImageService.countRemainImageGenerateAttempt(diaryId)

        return DiaryResponse(
            content = diary.content,
            targetDate = DateTimeUtil.toDefaultDateFormat(diary.targetDate),
            scope = diary.scope.name,
            createdAt = DateTimeUtil.toDefaultDateTimeFormat(diary.timestamp.createdAt),
            remainAttempt = remainCount,
            diaryImageUrl = diaryImage.image.url
        )
    }
}
