package org.grida.persistence.diaryimage

import org.grida.domain.image.ImageStatus
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.springframework.data.jpa.repository.JpaRepository

fun DiaryImageJpaEntityRepository.findByIdOrException(
    id: Long
): DiaryImageEntity {
    return this.findById(id).orElseThrow { GridaException(NoSuchData) }
}

interface DiaryImageJpaEntityRepository : JpaRepository<DiaryImageEntity, Long> {

    fun existsByDiaryIdAndStatus(diaryId: Long, status: ImageStatus): Boolean
}