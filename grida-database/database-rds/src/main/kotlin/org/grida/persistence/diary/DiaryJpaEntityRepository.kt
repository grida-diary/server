package org.grida.persistence.diary

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface DiaryJpaEntityRepository : JpaRepository<DiaryEntity, Long> {

    fun existsByUserIdAndTargetDate(userId: Long, targetDate: LocalDate): Boolean
}