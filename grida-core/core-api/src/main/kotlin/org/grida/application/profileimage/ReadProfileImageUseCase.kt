package org.grida.application.profileimage

import org.grida.datetime.DateTimeUtil
import org.grida.domain.profileimage.ProfileImageService
import org.springframework.stereotype.Component

@Component
class ReadProfileImageUseCase(
    private val profileImageService: ProfileImageService,
) {

    fun execute(
        profileImageId: Long,
    ): ProfileImageResponse {
        val profileImage = profileImageService.read(profileImageId)

        return ProfileImageResponse(
            profileImageId = profileImage.id,
            imageUrl = profileImage.image.url,
            status = profileImage.image.status.name,
            createdAt = DateTimeUtil.toDefaultDateTimeFormat(profileImage.image.timestamp.createdAt),
            appearance = profileImage.appearance
        )
    }
}
