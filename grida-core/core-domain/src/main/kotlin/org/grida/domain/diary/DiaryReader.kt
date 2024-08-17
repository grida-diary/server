package org.grida.domain.diary

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryReader(
    private val diaryRepository: DiaryRepository
) {

    @Transactional(readOnly = true)
    fun read(diaryId: Long): Diary {
        return diaryRepository.findById(diaryId)
    }
}
