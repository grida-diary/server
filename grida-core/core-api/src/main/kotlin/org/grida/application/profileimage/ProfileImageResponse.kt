package org.grida.application.profileimage

data class ProfileImageResponse(
    val profileImageId: Long,
    val imageUrl: String,
    val status: String,
    val createdAt: String,
    val appearance: String
)
