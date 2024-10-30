package org.grida.domain.diary

import org.grida.datetime.DateTimePicker
import org.grida.domain.diary.DiaryScope.PRIVATE
import org.grida.error.AccessFailed
import org.grida.error.CannotAppendDiaryAtDate
import org.grida.error.CannotAppendDiaryAtFuture
import org.grida.error.GridaException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Component
class DiaryValidator(
    private val diaryRepository: DiaryRepository,
) {

    @Transactional(readOnly = true)
    fun validateAlreadyExistsAtDate(
        userId: Long,
        targetDate: LocalDate,
    ) {
        if (diaryRepository.existsByUserIdAndTargetDate(userId, targetDate)) {
            throw GridaException(CannotAppendDiaryAtDate)
        }
    }

    fun validateIsPastThanToday(targetDate: LocalDate) {
        if (targetDate.isAfter(DateTimePicker.now().toLocalDate())) {
            throw GridaException(CannotAppendDiaryAtFuture)
        }
    }

    fun validateCanAccess(diary: Diary, userId: Long) {
        if (diary.scope == PRIVATE) {
            validateIsOwner(diary, userId)
        }
    }

    fun validateIsOwner(diary: Diary, userId: Long) {
        if (!diary.isOwner(userId)) {
            throw GridaException(AccessFailed)
        }
    }
}
