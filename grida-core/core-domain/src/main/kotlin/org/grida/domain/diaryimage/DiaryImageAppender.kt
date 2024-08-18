package org.grida.domain.diaryimage

import org.grida.domain.diary.DiaryReader
import org.grida.domain.user.UserReader
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryImageAppender(
    private val diaryImageRepository: DiaryImageRepository,
    private val diaryReader: DiaryReader,
    private val userReader: UserReader
) {

    @Transactional
    fun append(
        diaryImage: DiaryImage,
        diaryId: Long,
        userId: Long
    ): Long {
        val diary = diaryReader.read(diaryId)
        val user = userReader.read(userId)
        return diaryImageRepository.save(diaryImage, diary, user)
    }
}
