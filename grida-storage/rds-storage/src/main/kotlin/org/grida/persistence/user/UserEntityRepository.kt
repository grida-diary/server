package org.grida.persistence.user

import org.grida.domain.user.LoginOption
import org.grida.domain.user.User
import org.grida.domain.user.UserRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class UserEntityRepository(
    private val userJpaEntityRepository: UserJpaEntityRepository
) : UserRepository {

    @Transactional
    override fun save(user: User): Long {
        val userEntity = user.toEntity()
        userJpaEntityRepository.save(userEntity)
        return userEntity.id
    }

    override fun findById(id: Long): User {
        val userEntity = userJpaEntityRepository.findByIdOrException(id)
        return userEntity.toDomain()
    }

    override fun findByLoginOption(loginOption: LoginOption): User? {
        val userEntity = userJpaEntityRepository
            .findByPlatformAndPlatformIdentifierOrNull(loginOption.platform, loginOption.identifier)

        return userEntity?.toDomain()
    }

    override fun existsByLoginOption(loginOption: LoginOption): Boolean {
        return userJpaEntityRepository
            .existsByPlatformAndPlatformIdentifier(loginOption.platform, loginOption.identifier)
    }
}
