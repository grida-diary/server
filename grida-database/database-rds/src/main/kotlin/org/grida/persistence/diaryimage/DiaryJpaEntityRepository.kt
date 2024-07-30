package org.grida.persistence.diaryimage

import org.springframework.data.jpa.repository.JpaRepository

interface DiaryJpaEntityRepository : JpaRepository<DiaryImageEntity, Long> {
}