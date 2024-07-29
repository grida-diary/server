package org.grida.domain.diary

import org.springframework.stereotype.Service

@Service
class DiaryService(
    private val diaryAppender: DiaryAppender,
    private val diaryReader: DiaryReader,
    private val diaryModifier: DiaryModifier,
    private val diaryValidator: DiaryValidator
) {

    fun appendDiary(diary: Diary): Long {
        diaryValidator.validateIsPastThanToday(diary.targetDate)
        diaryValidator.validateAlreadyExistsAtDate(diary.userId, diary.targetDate)

        return diaryAppender.append(diary)
    }

    fun readDiary(
        diaryId: Long,
        userId: Long
    ): Diary {
        val diary = diaryReader.read(diaryId)
        diaryValidator.validateCanAccess(diary, userId)

        return diary
    }

    fun modify(
        diaryId: Long,
        userId: Long,
        content: String,
        scope: DiaryScope
    ): Long {
        diaryModifier.modify(diaryId, userId, content, scope)
        return diaryId
    }

    fun modifyScope(
        diaryId: Long,
        userId: Long,
        scope: DiaryScope
    ): Long {
        diaryModifier.modifyScope(diaryId, userId, scope)
        return diaryId
    }
}