package org.grida.persistence.image

import org.springframework.data.jpa.repository.JpaRepository

interface ImageJpaEntityRepository : JpaRepository<ImageEntity, Long>
