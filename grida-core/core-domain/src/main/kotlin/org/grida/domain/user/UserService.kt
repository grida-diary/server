package org.grida.domain.user

import org.grida.exception.UnusableUsernameException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun appendNormalUser(
        user: User,
        passwordConfirm: String
    ): Long {
        if (userRepository.existsByUsername(user.username)) throw UnusableUsernameException()

        return userRepository.save(user)
    }

    fun findUser(username: String): User {
        return userRepository.findByUsername(username)
    }
}
