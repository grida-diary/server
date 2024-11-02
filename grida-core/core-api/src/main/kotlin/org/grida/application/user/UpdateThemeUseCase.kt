package org.grida.application.user

import org.grida.domain.user.UserService
import org.springframework.stereotype.Component

@Component
class UpdateThemeUseCase(
    private val userService: UserService
) {

    fun execute(
        userId: Long,
        request: UpdateThemeRequest
    ) {
        userService.updateTheme(userId, request.theme)
    }
}
