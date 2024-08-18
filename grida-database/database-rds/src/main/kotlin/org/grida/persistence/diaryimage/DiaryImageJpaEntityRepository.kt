package org.grida.persistence.diaryimage

import org.grida.domain.image.ImageStatus
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

fun DiaryImageJpaEntityRepository.findByIdOrException(
    id: Long
): DiaryImageEntity {
    return this.findById(id)
        .orElseThrow { GridaException(NoSuchData(DiaryImageEntity::class, id)) }
}

fun DiaryImageJpaEntityRepository.findByDiaryIdAndStatusOrException(
    diaryId: Long,
    status: ImageStatus
): DiaryImageEntity {
    return this.findByDiaryIdAndStatus(diaryId, status)
        .orElseThrow { GridaException(NoSuchData(DiaryImageEntity::class, diaryId)) }
}

interface DiaryImageJpaEntityRepository : JpaRepository<DiaryImageEntity, Long> {

    fun findByDiaryIdAndStatus(diaryId: Long, status: ImageStatus): Optional<DiaryImageEntity>

    fun countByDiaryId(diaryId: Long): Long

    fun existsByDiaryIdAndStatus(diaryId: Long, status: ImageStatus): Boolean
}
