package org.grida.presentation.v1.profileimage.dto

import org.grida.datetime.withDefaultFormat
import org.grida.domain.profileimage.ProfileImage

data class ProfileImageResponse(
    val imageId: Long,
    val imageUrl: String,
    val status: String,
    val createdAt: String
) {

    companion object {
        fun from(profileImage: ProfileImage): ProfileImageResponse {
            return ProfileImageResponse(
                imageId = profileImage.id,
                imageUrl = profileImage.image.url,
                status = profileImage.image.status.name,
                createdAt = profileImage.image.timestamp.createdAt.withDefaultFormat()
            )
        }
    }
}
