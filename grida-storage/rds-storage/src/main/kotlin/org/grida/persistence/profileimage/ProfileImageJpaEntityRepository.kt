package org.grida.persistence.profileimage

import org.grida.domain.image.ImageStatus
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

fun ProfileImageJpaEntityRepository.findByIdOrException(id: Long): ProfileImageEntity {
    return this.findById(id)
        .orElseThrow { GridaException(NoSuchData(ProfileImageEntity::class, id)) }
}

fun ProfileImageJpaEntityRepository.findByUserIdAndStatusOrException(
    userId: Long,
    status: ImageStatus
): ProfileImageEntity {
    return this.findByUserIdAndStatus(userId, status)
        .orElseThrow { GridaException(NoSuchData(ProfileImageEntity::class, userId)) }
}

interface ProfileImageJpaEntityRepository : JpaRepository<ProfileImageEntity, Long> {

    fun findByUserIdAndStatus(userId: Long, status: ImageStatus): Optional<ProfileImageEntity>

    fun findAllByUserId(userId: Long): List<ProfileImageEntity>

    fun existsByUserIdAndStatus(userId: Long, status: ImageStatus): Boolean
}
