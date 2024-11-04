package org.grida.application.user

import org.grida.domain.user.UserService
import org.springframework.stereotype.Component

@Component
class ReadUserUseCase(
    private val userService: UserService
) {

    fun execute(userId: Long): ReadUserResponse {
        val user = userService.read(userId)
        return ReadUserResponse(
            userId = user.id,
            nickname = user.profile.nickname,
            gender = user.profile.gender.name,
            age = user.profile.age,
            user.theme
        )
    }
}
