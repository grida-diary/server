package org.grida.domain.diary

import org.springframework.stereotype.Service

@Service
class DiaryService(
    private val diaryAppender: DiaryAppender,
    private val diaryReader: DiaryReader,
    private val diaryValidator: DiaryValidator
) {

    fun appendDiary(diary: Diary): Long {
        diaryValidator.validateIsPastThanToday(diary.targetDate)
        diaryValidator.validateAlreadyExistsAtDate(diary.userId, diary.targetDate)

        return diaryAppender.append(diary)
    }

    fun readDiary(diaryId: Long, userId: Long): Diary {
        return diaryReader.read(diaryId, userId)
    }
}