package org.grida.persistence.user

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
        val userEntity = userJpaEntityRepository.save(UserEntity.from(user))
        return userEntity.id
    }

    override fun findById(id: Long): User {
        val userEntity = userJpaEntityRepository.findByIdOrException(id)
        return userEntity.toUser()
    }

    override fun findByUsername(username: String): User {
        val userEntity = userJpaEntityRepository.findByUsernameOrException(username)
        return userEntity.toUser()
    }

    override fun existsByUsername(username: String): Boolean {
        return userJpaEntityRepository.existsByUsername(username)
    }
}
