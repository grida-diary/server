package org.grida.persistence.diaryimage

import org.grida.domain.diary.Diary
import org.grida.domain.diaryimage.DiaryImage
import org.grida.domain.diaryimage.DiaryImageRepository
import org.grida.domain.image.ImageStatus
import org.grida.domain.user.User
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class DiaryImageEntityRepository(
    private val diaryImageJpaEntityRepository: DiaryImageJpaEntityRepository
) : DiaryImageRepository {

    @Transactional
    override fun save(
        diaryImage: DiaryImage,
        diary: Diary,
        user: User
    ): Long {
        val diaryEntity = diaryImage.toEntity(user, diary)
        diaryImageJpaEntityRepository.save(diaryEntity)
        return diaryEntity.id
    }

    override fun findById(id: Long): DiaryImage {
        val diaryImageEntity = diaryImageJpaEntityRepository.findByIdOrException(id)
        return diaryImageEntity.toDomain()
    }

    override fun findByDiaryIdAndStatus(diaryId: Long, status: ImageStatus): DiaryImage {
        val diaryImageEntity =
            diaryImageJpaEntityRepository.findByDiaryIdAndStatusOrException(diaryId, status)
        return diaryImageEntity.toDomain()
    }

    override fun countByDiaryId(diaryId: Long): Long {
        return diaryImageJpaEntityRepository.countByDiaryId(diaryId)
    }

    override fun existsByDiaryIdAndStatus(diaryId: Long, status: ImageStatus): Boolean {
        return diaryImageJpaEntityRepository.existsByDiaryIdAndStatus(diaryId, status)
    }

    @Transactional
    override fun updateStatus(
        diaryImageId: Long,
        status: ImageStatus
    ): Long {
        val diaryImageEntity = diaryImageJpaEntityRepository.findByIdOrException(diaryImageId)
        diaryImageEntity.updateStatue(status)
        return diaryImageEntity.id
    }
}
