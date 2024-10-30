package org.grida.persistence.diary

import org.springframework.data.jpa.repository.JpaRepository

interface DiaryJpaEntityRepository : JpaRepository<DiaryEntity, Long>
