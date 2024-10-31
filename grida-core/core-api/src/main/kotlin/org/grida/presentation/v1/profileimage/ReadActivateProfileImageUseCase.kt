package org.grida.presentation.v1.profileimage

import org.grida.datetime.DateTimeUtil
import org.grida.domain.profileimage.ProfileImageService
import org.springframework.stereotype.Component

@Component
class ReadActivateProfileImageUseCase(
    private val profileImageService: ProfileImageService
) {

    fun execute(userId: Long): ProfileImageResponse {
        val profileImage = profileImageService.readActivateProfileImage(userId)

        return ProfileImageResponse(
            imageId = profileImage.id,
            imageUrl = profileImage.image.url,
            status = profileImage.image.status.name,
            createdAt = DateTimeUtil.toDefaultDateTimeFormat(profileImage.image.timestamp.createdAt),
            gender = profileImage.appearance.gender.name,
            age = profileImage.appearance.age,
            hairStyle = profileImage.appearance.hairStyle,
            glasses = profileImage.appearance.glasses,
            bodyShape = profileImage.appearance.bodyShape
        )
    }
}
