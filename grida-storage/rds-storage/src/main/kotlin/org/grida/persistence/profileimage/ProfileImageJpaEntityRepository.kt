package org.grida.persistence.profileimage

import org.springframework.data.jpa.repository.JpaRepository

interface ProfileImageJpaEntityRepository : JpaRepository<ProfileImageEntity, Long>
