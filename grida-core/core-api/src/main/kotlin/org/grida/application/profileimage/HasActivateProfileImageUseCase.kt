package org.grida.application.profileimage

import org.grida.api.dto.BooleanResultResponse
import org.grida.domain.profileimage.ProfileImageService
import org.springframework.stereotype.Component

@Component
class HasActivateProfileImageUseCase(
    private val profileImageService: ProfileImageService
) {

    fun execute(userId: Long): BooleanResultResponse {
        val result = profileImageService.hasActivateProfileImage(userId)
        return BooleanResultResponse(result)
    }
}
