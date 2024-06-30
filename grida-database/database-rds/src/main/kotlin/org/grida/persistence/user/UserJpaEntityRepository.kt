package org.grida.persistence.user

import org.grida.exception.UserNotExistsException
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

fun UserJpaEntityRepository.findByIdOrException(id: Long): UserEntity {
    return this.findById(id)
        .orElseThrow { throw UserNotExistsException() }
}

fun UserJpaEntityRepository.findByUsernameOrException(username: String): UserEntity {
    return this.findByUsername(username)
        .orElseThrow { throw UserNotExistsException() }
}

interface UserJpaEntityRepository : JpaRepository<UserEntity, Long> {

    fun findByUsername(username: String): Optional<UserEntity>

    fun existsByUsername(username: String): Boolean
}
