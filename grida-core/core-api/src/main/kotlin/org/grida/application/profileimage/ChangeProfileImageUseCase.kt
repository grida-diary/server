package org.grida.application.profileimage

import org.grida.api.dto.IdResponse
import org.grida.domain.profileimage.ProfileImageService
import org.springframework.stereotype.Component

@Component
class ChangeProfileImageUseCase(
    private val profileImageService: ProfileImageService,
) {

    fun execute(
        userId: Long,
        profileImageId: Long,
    ): IdResponse {
        profileImageService.changeProfileImage(userId, profileImageId)
        return IdResponse(userId)
    }
}
