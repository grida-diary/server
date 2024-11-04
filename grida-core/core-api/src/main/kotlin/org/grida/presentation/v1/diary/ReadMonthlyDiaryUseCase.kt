package org.grida.presentation.v1.diary

import org.grida.domain.diary.DiaryService
import org.grida.domain.diaryimage.DiaryImageService
import org.springframework.stereotype.Component

@Component
class ReadMonthlyDiaryUseCase(
    private val diaryService: DiaryService,
    private val diaryImageService: DiaryImageService
) {

    fun execute(
        userId: Long,
        year: Int,
        month: Int
    ): MonthlyDiaryResponse {
        val diaries = diaryService.readMonthlyDiaries(userId, year, month)
        val diaryImages = diaryImageService.readDiaryImagesByDiaryIds(diaries.map { it.id })

        val diaryImagesMap = diaryImages.associateBy { it.diaryId }

        val diaryMap = diaries.associateBy(
            keySelector = { it.targetDate.dayOfMonth },
            valueTransform = {
                MonthlyDiaryResponse.DiaryResponse(
                    diaryId = it.id,
                    diaryImageUrl = diaryImagesMap[it.id]!!.image.url,
                    targetDate = it.targetDate.toString(),
                    content = it.content
                )
            }
        )

        return MonthlyDiaryResponse(year, month, diaryMap)
    }
}
