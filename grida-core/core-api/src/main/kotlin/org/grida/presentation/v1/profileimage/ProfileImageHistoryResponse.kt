package org.grida.presentation.v1.profileimage

data class ProfileImageHistoryResponse(
    val count: Int,
    val profileImages: List<ProfileImageResponse>,
)
