package org.grida.application.user

import org.grida.domain.user.UserProfile

data class UpdateProfileRequest(
    val nickname: String,
    val gender: UserProfile.Gender,
    val age: Int,
)
