package org.grida.domain.diary

import org.grida.domain.user.UserReader
import org.springframework.stereotype.Component

@Component
class DiaryAppender(
    private val diaryRepository: DiaryRepository,
    private val userReader: UserReader
) {

    fun append(
        diary: Diary
    ): Long {
        val targetUser = userReader.read(diary.userId)
        return diaryRepository.save(diary, targetUser)
    }
}
