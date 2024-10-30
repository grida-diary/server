package org.grida.persistence.diaryimage

import org.grida.domain.diary.Diary
import org.grida.domain.diaryimage.DiaryImage
import org.grida.domain.diaryimage.DiaryImageRepository
import org.grida.domain.image.ImageStatus
import org.grida.domain.user.User
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.grida.persistence.base.JpqlExecutor
import org.grida.persistence.diary.DiaryEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Repository
@Transactional(readOnly = true)
class DiaryImageEntityRepository(
    private val diaryImageJpaEntityRepository: DiaryImageJpaEntityRepository,
    private val jpqlExecutor: JpqlExecutor,
) : DiaryImageRepository {

    @Transactional
    override fun save(
        diaryImage: DiaryImage,
        diary: Diary,
        user: User,
    ): Long {
        val diaryEntity = diaryImage.toEntity(user, diary)
        diaryImageJpaEntityRepository.save(diaryEntity)
        return diaryEntity.id
    }

    override fun findById(id: Long): DiaryImage? {
        val diaryImageEntity = diaryImageJpaEntityRepository.findById(id)
        return diaryImageEntity.getOrNull()?.toDomain()
    }

    override fun findByDiaryIdAndStatus(diaryId: Long, status: ImageStatus): DiaryImage? {
        val diaryImageEntity = jpqlExecutor.find {
            select(
                entity(DiaryImageEntity::class)
            ).from(
                entity(DiaryImageEntity::class)
            ).whereAnd(
                path(DiaryImageEntity::diary)(DiaryEntity::id).eq(diaryId),
                path(DiaryImageEntity::status).eq(status)
            )
        }

        return diaryImageEntity?.toDomain()
    }

    override fun countByDiaryId(diaryId: Long): Long {
        val count = jpqlExecutor.find {
            select(
                count(entity(DiaryImageEntity::class))
            ).from(
                entity(DiaryImageEntity::class)
            ).where(
                path(DiaryImageEntity::diary)(DiaryEntity::id).eq(diaryId)
            )
        }

        return count ?: 0
    }

    override fun existsByDiaryIdAndStatus(diaryId: Long, status: ImageStatus): Boolean {
        return findByDiaryIdAndStatus(diaryId, status)?.let { true } ?: false
    }

    @Transactional
    override fun updateStatus(
        diaryImageId: Long,
        status: ImageStatus,
    ): Long {
        val diaryImageEntity = diaryImageJpaEntityRepository.findById(diaryImageId)
            .orElseThrow { GridaException(NoSuchData(DiaryImage::class, diaryImageId)) }
        diaryImageEntity.updateStatue(status)
        return diaryImageEntity.id
    }
}
