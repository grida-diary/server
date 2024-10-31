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
import org.grida.persistence.image.ImageEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

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

    override fun findById(id: Long): DiaryImage {
        val diaryImageEntity = findEntityByIdWithFetchJoin(id)
        return diaryImageEntity?.toDomain()
            ?: throw GridaException(NoSuchData(DiaryImageEntity::class, id))
    }

    override fun findByDiaryIdAndStatus(diaryId: Long, status: ImageStatus): DiaryImage {
        val diaryImageEntity = jpqlExecutor.find {
            select(
                entity(DiaryImageEntity::class)
            ).from(
                entity(DiaryImageEntity::class)
            ).whereAnd(
                path(DiaryImageEntity::diary)(DiaryEntity::id).eq(diaryId),
                path(DiaryImageEntity::image)(ImageEntity::status).eq(status)
            )
        }

        return diaryImageEntity?.toDomain()
            ?: throw GridaException(NoSuchData(DiaryImageEntity::class, diaryId))
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
        val count = jpqlExecutor.find {
            select(
                count(entity(DiaryImageEntity::class))
            ).from(
                entity(DiaryImageEntity::class)
            ).whereAnd(
                path(DiaryImageEntity::diary)(DiaryEntity::id).eq(diaryId),
                path(DiaryImageEntity::image)(ImageEntity::status).eq(status)
            )
        }

        return count!! > 0
    }

    @Transactional
    override fun updateStatus(
        diaryImageId: Long,
        status: ImageStatus,
    ): Long {
        val diaryImageEntity = findEntityByIdWithFetchJoin(diaryImageId)
            ?: throw GridaException(NoSuchData(DiaryImage::class, diaryImageId))
        val image = diaryImageEntity.image
        image.updateStatue(status)

        return diaryImageEntity.id
    }

    private fun findEntityByIdWithFetchJoin(id: Long): DiaryImageEntity? {
        return jpqlExecutor.find {
            select(
                entity(DiaryImageEntity::class)
            ).from(
                entity(DiaryImageEntity::class),
                fetchJoin(DiaryImageEntity::image)
            ).where(
                path(DiaryImageEntity::id).eq(id)
            )
        }
    }
}
