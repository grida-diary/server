package org.grida.persistence.diary

import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

fun DiaryJpaEntityRepository.findByIdOrException(id: Long): DiaryEntity {
    return this.findById(id)
        .orElseThrow { GridaException(NoSuchData(DiaryEntity::class, id)) }
}

interface DiaryJpaEntityRepository : JpaRepository<DiaryEntity, Long> {

    fun existsByUserIdAndTargetDate(userId: Long, targetDate: LocalDate): Boolean
}
