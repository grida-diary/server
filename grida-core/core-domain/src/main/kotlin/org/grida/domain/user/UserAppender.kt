package org.grida.domain.user

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserAppender(
    private val userRepository: UserRepository,
    private val userValidator: UserValidator
) {

    @Transactional
    fun append(user: User): Long {
        userValidator.validateUsernameAlreadyInUse(user.username)
        return userRepository.save(user)
    }
}