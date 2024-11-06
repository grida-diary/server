package org.grida.application.profileimage

data class ProfileImageHistoryResponse(
    val count: Int,
    val profileImages: List<ProfileImageResponse>,
)
