package org.grida.persistence.diary

import org.grida.domain.diary.Diary
import org.grida.domain.diary.DiaryRepository
import org.grida.domain.diary.DiaryScope
import org.grida.domain.user.User
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.grida.persistence.base.JpqlExecutor
import org.grida.persistence.user.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Repository
@Transactional(readOnly = true)
class DiaryEntityRepository(
    private val diaryJpaEntityRepository: DiaryJpaEntityRepository,
    private val jpqlExecutor: JpqlExecutor,
) : DiaryRepository {

    @Transactional
    override fun save(
        diary: Diary,
        user: User,
    ): Long {
        val diaryEntity = diary.toEntity(user)
        diaryJpaEntityRepository.save(diaryEntity)
        return diaryEntity.id
    }

    override fun findById(id: Long): Diary {
        val diaryEntity = diaryJpaEntityRepository.findById(id)
            .orElseThrow { GridaException(NoSuchData(Diary::class, id)) }
        return diaryEntity.toDomain()
    }

    override fun existsByUserIdAndTargetDate(
        userId: Long,
        targetDate: LocalDate,
    ): Boolean {
        val count = jpqlExecutor.find {
            select(
                count(entity(DiaryEntity::class))
            ).from(
                entity(DiaryEntity::class)
            ).whereAnd(
                path(DiaryEntity::user)(UserEntity::id).eq(userId),
                path(DiaryEntity::targetDate).eq(targetDate)
            )
        }

        return (count ?: 0) > 0
    }

    @Transactional
    override fun updateContent(diaryId: Long, content: String): Long {
        val diaryEntity = diaryJpaEntityRepository.findById(diaryId)
            .orElseThrow { GridaException(NoSuchData(Diary::class, diaryId)) }
        diaryEntity.updateContent(content)
        return diaryEntity.id
    }

    @Transactional
    override fun updateScope(diaryId: Long, scope: DiaryScope): Long {
        val diaryEntity = diaryJpaEntityRepository.findById(diaryId)
            .orElseThrow { GridaException(NoSuchData(Diary::class, diaryId)) }
        diaryEntity.updateScope(scope)
        return diaryEntity.id
    }
}
