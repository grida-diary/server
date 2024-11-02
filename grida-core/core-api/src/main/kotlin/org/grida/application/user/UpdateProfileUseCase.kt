package org.grida.application.user

import org.grida.domain.user.UserProfile
import org.grida.domain.user.UserService
import org.springframework.stereotype.Component

@Component
class UpdateProfileUseCase(
    private val userService: UserService
) {

    fun execute(
        userId: Long,
        request: UpdateProfileRequest
    ) {
        val profile = UserProfile(
            nickname = request.nickname,
            gender = request.gender,
            age = request.age
        )
        userService.updateProfile(userId, profile)
    }
}
