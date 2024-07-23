package org.grida.domain.user

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userAppender: UserAppender,
    private val userReader: UserReader,
    private val userValidator: UserValidator
) {

    fun appendNormalUser(
        user: User,
        passwordConfirm: String
    ): Long {
        userValidator.validatePasswordConfirmMatches(user.password, passwordConfirm)
        return userAppender.append(user)
    }

    fun findUser(username: String): User {
        return userReader.read(username)
    }
}
