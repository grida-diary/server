package org.grida.domain.diaryimage

import org.grida.domain.diary.Diary
import org.grida.domain.image.ImageStatus
import org.grida.domain.user.User

interface DiaryImageRepository {
    fun save(diaryImage: DiaryImage, diary: Diary, user: User): Long

    fun findById(id: Long): DiaryImage

    fun countByDiaryId(diaryId: Long): Long

    fun existsByDiaryIdAndStatus(diaryId: Long, status: ImageStatus): Boolean

    fun updateStatus(diaryImageId: Long, status: ImageStatus): Long
}