package org.grida.domain.user

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userAppender: UserAppender,
    private val userReader: UserReader
) {

    fun appendNormalUser(
        name: String,
        loginOption: LoginOption
    ): Long {
        val user = User(
            name = name,
            loginOption = loginOption
        )
        return userAppender.append(user)
    }

    fun appendAndReturnNormalUser(
        name: String,
        loginOption: LoginOption
    ): User {
        val user = User(
            name = name,
            loginOption = loginOption
        )
        return userAppender.appendAndReturnUser(user)
    }

    fun readUserByLoginOption(
        loginOption: LoginOption
    ): User? {
        return userReader.readByLoginOptionOrNull(loginOption)
    }
}
