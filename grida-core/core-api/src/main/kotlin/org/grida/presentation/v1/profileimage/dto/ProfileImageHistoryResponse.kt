package org.grida.presentation.v1.profileimage.dto

import org.grida.domain.profileimage.ProfileImage

data class ProfileImageHistoryResponse(
    val count: Int,
    val profileImages: List<ProfileImageResponse>
) {

    companion object {
        fun from(profileImages: List<ProfileImage>): ProfileImageHistoryResponse {
            return ProfileImageHistoryResponse(
                count = profileImages.size,
                profileImages = profileImages.map { ProfileImageResponse.from(it) }
            )
        }
    }
}
