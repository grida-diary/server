package org.grida.persistence.user

import org.grida.domain.user.LoginOption
import org.grida.domain.user.User
import org.grida.domain.user.UserRepository
import org.grida.persistence.base.JpqlExecutor
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Repository
@Transactional(readOnly = true)
class UserEntityRepository(
    private val userJpaEntityRepository: UserJpaEntityRepository,
    private val jpqlExecutor: JpqlExecutor
) : UserRepository {

    @Transactional
    override fun save(user: User): Long {
        val userEntity = user.toEntity()
        userJpaEntityRepository.save(userEntity)
        return userEntity.id
    }

    override fun findById(id: Long): User? {
        val userEntity = userJpaEntityRepository.findById(id)
        return userEntity.getOrNull()?.toDomain()
    }

    override fun findByLoginOption(loginOption: LoginOption): User? {
        val userEntity = jpqlExecutor.find {
            select(
                entity(UserEntity::class)
            ).from(
                entity(UserEntity::class)
            ).whereAnd(
                path(UserEntity::platform).eq(loginOption.platform),
                path(UserEntity::platformIdentifier).eq(loginOption.identifier)
            )
        }

        return userEntity?.toDomain()
    }

    override fun existsByLoginOption(loginOption: LoginOption): Boolean {
        return findByLoginOption(loginOption)?.let { true } ?: false
    }
}
