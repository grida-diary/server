package org.grida.presentation.v1.profileimage

import org.grida.api.dto.IdResponse
import org.grida.domain.profileimage.ProfileImageService
import org.springframework.stereotype.Component

@Component
class ApplyProfileImageUseCase(
    private val profileImageService: ProfileImageService,
) {

    fun execute(
        userId: Long,
        profileImageId: Long,
    ): IdResponse {
        profileImageService.applyProfileImage(userId, profileImageId)
        return IdResponse(userId)
    }
}
