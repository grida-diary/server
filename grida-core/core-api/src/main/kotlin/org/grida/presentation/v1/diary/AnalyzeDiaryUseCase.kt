package org.grida.presentation.v1.diary

import org.grida.domain.diary.DiaryService
import org.grida.domain.diaryimage.DiaryImageService
import org.grida.domain.user.UserService
import org.grida.provider.diaryimage.DiaryAnalyzer
import org.grida.provider.diaryimage.DiaryImageKey
import org.springframework.stereotype.Component

@Component
class AnalyzeDiaryUseCase(
    private val diaryImageService: DiaryImageService,
    private val diaryService: DiaryService,
    private val userService: UserService,
    private val diaryAnalyzer: DiaryAnalyzer
) {

    fun execute(
        userId: Long,
        diaryId: Long
    ): AnalyzeDiaryResponse {
        val diary = diaryService.readDiary(diaryId, userId)
        val user = userService.read(userId)
        val key = DiaryImageKey(
            diary = diary.content,
            theme = user.theme,
            gender = user.profile.gender.name,
            age = user.profile.age
        )

        val result = diaryAnalyzer.analyze(key)

        return AnalyzeDiaryResponse(result.value, result.comment)
    }
}
