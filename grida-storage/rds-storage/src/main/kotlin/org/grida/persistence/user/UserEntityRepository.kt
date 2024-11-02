package org.grida.persistence.user

import org.grida.domain.user.LoginOption
import org.grida.domain.user.User
import org.grida.domain.user.UserProfile
import org.grida.domain.user.UserRepository
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.grida.persistence.base.JpqlExecutor
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class UserEntityRepository(
    private val userJpaEntityRepository: UserJpaEntityRepository,
    private val jpqlExecutor: JpqlExecutor,
) : UserRepository {

    @Transactional
    override fun save(user: User): Long {
        val userEntity = user.toEntity()
        userJpaEntityRepository.save(userEntity)
        return userEntity.id
    }

    override fun findById(id: Long): User {
        val userEntity = userJpaEntityRepository.findById(id)
            .orElseThrow { GridaException(NoSuchData(User::class, id)) }
        return userEntity.toDomain()
    }

    override fun findByLoginOption(loginOption: LoginOption): User {
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
            ?: throw GridaException(NoSuchData(User::class, loginOption.identifier))
    }

    override fun findByLoginOptionOrNull(loginOption: LoginOption): User? {
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
        val count = jpqlExecutor.find {
            select(
                count(entity(UserEntity::class))
            ).from(
                entity(UserEntity::class)
            ).whereAnd(
                path(UserEntity::platform).eq(loginOption.platform),
                path(UserEntity::platformIdentifier).eq(loginOption.identifier)
            )
        }

        return count!! > 0
    }

    override fun updateProfileById(id: Long, userProfile: UserProfile) {
        val userEntity = userJpaEntityRepository.findById(id)
            .orElseThrow { GridaException(NoSuchData(User::class, id)) }

        userEntity.updateProfile(userProfile)
    }

    override fun updateThemeById(id: Long, theme: String) {
        val userEntity = userJpaEntityRepository.findById(id)
            .orElseThrow { GridaException(NoSuchData(User::class, id)) }

        userEntity.updateTheme(theme)
    }
}
