package org.grida.domain.diaryimage

import org.grida.domain.diary.Diary
import org.grida.domain.user.User

interface DiaryImageRepository {
    fun save(diaryImage: DiaryImage, diary: Diary, user: User): Long
}