package org.grida.persistence.diary

import org.grida.domain.diary.Diary
import org.grida.domain.diary.DiaryRepository
import org.grida.domain.diary.DiaryScope
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
        val diaryEntity = diary.toEntity(user)
        diaryJpaEntityRepository.save(diaryEntity)
        return diaryEntity.id
    }

    override fun findById(id: Long): Diary {
        val diaryEntity = diaryJpaEntityRepository.findByIdOrException(id)
        return diaryEntity.toDomain()
    }

    override fun existsByUserIdAndTargetDate(
        userId: Long,
        targetDate: LocalDate
    ): Boolean {
        return diaryJpaEntityRepository.existsByUserIdAndTargetDate(userId, targetDate)
    }

    @Transactional
    override fun updateContent(diaryId: Long, content: String): Long {
        val diaryEntity = diaryJpaEntityRepository.findByIdOrException(diaryId)
        diaryEntity.updateContent(content)
        return diaryEntity.id
    }

    @Transactional
    override fun updateScope(diaryId: Long, scope: DiaryScope): Long {
        val diaryEntity = diaryJpaEntityRepository.findByIdOrException(diaryId)
        diaryEntity.updateScope(scope)
        return diaryEntity.id
    }
}
