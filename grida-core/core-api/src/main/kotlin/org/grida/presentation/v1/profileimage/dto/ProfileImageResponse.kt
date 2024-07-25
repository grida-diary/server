package org.grida.presentation.v1.profileimage.dto

import org.grida.datetime.withDefaultFormat
import org.grida.domain.profileimage.ProfileImage

data class ProfileImageResponse(
    val imageId: Long,
    val imageUrl: String,
    val status: String,
    val createdAt: String,
    val gender: String,
    val age: Int,
    val hairStyle: String,
    val glasses: String,
    val bodyShape: String
) {

    companion object {
        fun from(profileImage: ProfileImage): ProfileImageResponse {
            return ProfileImageResponse(
                imageId = profileImage.id,
                imageUrl = profileImage.image.url,
                status = profileImage.image.status.name,
                createdAt = profileImage.image.timestamp.createdAt.withDefaultFormat(),
                gender = profileImage.appearance.gender.name,
                age = profileImage.appearance.age,
                hairStyle = profileImage.appearance.hairStyle,
                glasses = profileImage.appearance.glasses,
                bodyShape = profileImage.appearance.bodyShape
            )
        }
    }
}
