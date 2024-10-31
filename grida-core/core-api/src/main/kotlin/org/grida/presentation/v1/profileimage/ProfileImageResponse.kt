package org.grida.presentation.v1.profileimage

data class ProfileImageResponse(
    val imageId: Long,
    val imageUrl: String,
    val status: String,
    val createdAt: String,
    val gender: String,
    val age: Int,
    val hairStyle: String,
    val glasses: String,
    val bodyShape: String,
)
