package org.grida.persistence.user

import org.grida.domain.user.LoginPlatform
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

fun UserJpaEntityRepository.findByIdOrException(id: Long): UserEntity {
    return this.findById(id)
        .orElseThrow { throw GridaException(NoSuchData(UserEntity::class, id)) }
}

fun UserJpaEntityRepository.findByPlatformAndPlatformIdentifierOrNull(
    platform: LoginPlatform,
    platformIdentifier: String
): UserEntity? {
    return this.findByPlatformAndPlatformIdentifier(platform, platformIdentifier)
        .orElse(null)
}

interface UserJpaEntityRepository : JpaRepository<UserEntity, Long> {

    fun findByPlatformAndPlatformIdentifier(
        platform: LoginPlatform,
        platformIdentifier: String
    ): Optional<UserEntity>

    fun existsByPlatformAndPlatformIdentifier(
        platform: LoginPlatform,
        platformIdentifier: String
    ): Boolean
}
