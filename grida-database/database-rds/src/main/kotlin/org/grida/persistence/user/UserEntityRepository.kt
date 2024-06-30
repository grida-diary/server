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
        val createdUser = userJpaEntityRepository.save(UserEntity.from(user))
        return createdUser.id
    }

    override fun findByUsername(username: String): User {
        val user = userJpaEntityRepository.findByUsernameOrException(username)
        return user.toUser()
    }

    override fun existsByUsername(username: String): Boolean {
        return userJpaEntityRepository.existsByUsername(username)
    }
}
