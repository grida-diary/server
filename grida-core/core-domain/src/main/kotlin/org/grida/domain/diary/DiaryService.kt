package org.grida.domain.diary

import org.grida.datetime.DateTimePicker
import org.grida.domain.base.AccessManager
import org.grida.domain.user.UserRepository
import org.grida.error.CannotAppendDiaryAtDate
import org.grida.error.CannotAppendDiaryAtFuture
import org.grida.error.GridaException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DiaryService(
    private val diaryRepository: DiaryRepository,
    private val userRepository: UserRepository,
    private val accessManager: AccessManager,
) {

    @Transactional
    fun appendDiary(diary: Diary): Long {
        if (diary.isDateAfter(DateTimePicker.now().toLocalDate())) {
            throw GridaException(CannotAppendDiaryAtFuture)
        }

        if (diaryRepository.existsByUserIdAndTargetDate(diary.userId, diary.targetDate)) {
            throw GridaException(CannotAppendDiaryAtDate)
        }

        val targetUser = userRepository.findById(diary.userId)
        return diaryRepository.save(diary, targetUser)
    }

    fun readDiary(
        diaryId: Long,
        userId: Long,
    ): Diary {
        return diaryRepository.findById(diaryId)
    }

    @Transactional
    fun modify(
        diaryId: Long,
        userId: Long,
        content: String,
    ): Long {
        val diary = diaryRepository.findById(diaryId)
        accessManager.ownerOnly(diary, userId)
        diaryRepository.updateContent(diaryId, content)
        return diaryId
    }
}
