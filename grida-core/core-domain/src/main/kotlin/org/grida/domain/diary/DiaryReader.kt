package org.grida.domain.diary

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryReader(
    private val diaryRepository: DiaryRepository,
    private val diaryValidator: DiaryValidator
) {

    @Transactional(readOnly = true)
    fun read(diaryId: Long, userId: Long): Diary {
        val diary = diaryRepository.findById(diaryId)
        diaryValidator.validateCanAccess(diary, userId)
        return diary
    }
}