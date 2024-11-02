package org.grida.domain.user

import org.grida.domain.base.Timestamp

data class User(
    val id: Long = 0,
    val timestamp: Timestamp = Timestamp(),
    val role: Role = Role.ROLE_USER,
    val loginOption: LoginOption,

    val profile: UserProfile = UserProfile(),

    val theme: String = "",
)
