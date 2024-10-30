package org.grida.persistence.diaryimage

import org.springframework.data.jpa.repository.JpaRepository

interface DiaryImageJpaEntityRepository : JpaRepository<DiaryImageEntity, Long>
