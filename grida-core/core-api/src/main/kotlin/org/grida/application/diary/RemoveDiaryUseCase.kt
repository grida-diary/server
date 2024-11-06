package org.grida.application.diary

import org.grida.domain.diary.DiaryService
import org.springframework.stereotype.Component

@Component
class RemoveDiaryUseCase(
    private val diaryService: DiaryService
) {
}