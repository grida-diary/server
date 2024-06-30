package org.grida.presentation.v1.user.dto

import org.grida.domain.user.User

data class SignInRequest(
    val username: String,
    val password: String,
    val nickname: String
) {
    fun toUser(): User {
        return User(
            username = username,
            password = password,
            nickname = nickname
        )
    }
}
