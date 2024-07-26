package org.grida.persistence.diary

import org.grida.domain.diary.Diary
import org.grida.domain.diary.DiaryRepository
import org.grida.domain.user.User
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Repository
@Transactional(readOnly = true)
class DiaryEntityRepository(
    private val diaryJpaEntityRepository: DiaryJpaEntityRepository
) : DiaryRepository {

    @Transactional
    override fun save(
        diary: Diary,
        user: User
    ): Long {
        val diaryEntity = diaryJpaEntityRepository.save(DiaryEntity.from(diary, user))
        return diaryEntity.id
    }

    override fun existsByUserIdAndTargetDate(
        userId: Long,
        targetDate: LocalDate
    ): Boolean {
        return diaryJpaEntityRepository.existsByUserIdAndTargetDate(userId, targetDate)
    }
}