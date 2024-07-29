package org.grida.domain.diary

import org.grida.domain.user.UserReader
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryAppender(
    private val diaryRepository: DiaryRepository,
    private val userReader: UserReader
) {

    @Transactional
    fun append(
        diary: Diary
    ): Long {
        val targetUser = userReader.read(diary.userId)
        return diaryRepository.save(diary, targetUser)
    }
}
