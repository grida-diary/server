package org.grida.domain.diary

import org.grida.domain.user.User
import java.time.LocalDate

interface DiaryRepository {

    fun save(diary: Diary, user: User): Long

    fun findById(id: Long): Diary

    fun existsByUserIdAndTargetDate(userId: Long, targetDate: LocalDate): Boolean
}